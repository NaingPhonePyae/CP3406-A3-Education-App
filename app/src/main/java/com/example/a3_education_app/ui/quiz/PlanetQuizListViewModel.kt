package com.example.a3_education_app.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a3_education_app.SpaceEducationApplication
import com.example.a3_education_app.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class PlanetQuizListViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    fun highScoreFlow(quizId: String): StateFlow<Int> =
        userPreferencesRepository.highScoreFlow(quizId)
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
