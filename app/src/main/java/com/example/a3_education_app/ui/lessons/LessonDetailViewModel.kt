package com.example.a3_education_app.ui.lessons

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
import kotlinx.coroutines.launch

class LessonDetailViewModel(
    private val lessonId: String,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val isCompleted: StateFlow<Boolean> =
        userPreferencesRepository.isLessonCompletedFlow(lessonId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    fun markComplete() {
        viewModelScope.launch {
            userPreferencesRepository.setLessonCompleted(lessonId, true)
        }
    }

    companion object {
        fun factory(lessonId: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as SpaceEducationApplication
                LessonDetailViewModel(
                    lessonId = lessonId,
                    userPreferencesRepository = app.container.userPreferencesRepository
                )
            }
        }
    }
}