package com.example.a3_education_app.ui.lessons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a3_education_app.SpaceEducationApplication
import com.example.a3_education_app.data.LessonDataSource
import com.example.a3_education_app.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class LessonsViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    fun isCompletedFlow(lessonId: String): StateFlow<Boolean> =
        userPreferencesRepository.isLessonCompletedFlow(lessonId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    val completedCount: StateFlow<Int> = combine(
        LessonDataSource.lessons.map { lesson ->
            userPreferencesRepository.isLessonCompletedFlow(lesson.id)
        }
    ) { values -> values.count { it } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    val totalLessons: Int = LessonDataSource.lessons.size

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as SpaceEducationApplication
                LessonsViewModel(app.container.userPreferencesRepository)
            }
        }
    }
}