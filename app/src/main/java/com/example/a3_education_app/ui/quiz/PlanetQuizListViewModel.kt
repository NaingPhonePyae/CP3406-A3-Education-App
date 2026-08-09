package com.example.a3_education_app.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a3_education_app.SpaceEducationApplication
import com.example.a3_education_app.data.SolarSystemDataSource
import com.example.a3_education_app.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class PlanetQuizListViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val quizIds: List<String> =
        SolarSystemDataSource.bodies.map { it.id } + ExoplanetQuizViewModel.QUIZ_ID

    val totalQuizzes: Int = quizIds.size

    fun highScoreFlow(quizId: String): StateFlow<Int> =
        userPreferencesRepository.highScoreFlow(quizId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    fun isCompletedFlow(quizId: String): StateFlow<Boolean> =
        userPreferencesRepository.isQuizCompletedFlow(quizId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    val completedCount: StateFlow<Int> = combine(
        quizIds.map { id -> userPreferencesRepository.isQuizCompletedFlow(id) }
    ) { values -> values.count { it } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as SpaceEducationApplication
                PlanetQuizListViewModel(app.container.userPreferencesRepository)
            }
        }
    }
}