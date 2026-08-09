package com.example.a3_education_app.ui.settings

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

class SettingsViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val isDarkTheme: StateFlow<Boolean> =
        userPreferencesRepository.darkThemeFlow()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    val blitzQuestionCount: StateFlow<Int> =
        userPreferencesRepository.blitzQuestionCountFlow()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 5)

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setDarkTheme(enabled)
        }
    }

    fun setBlitzQuestionCount(count: Int) {
        if (count !in listOf(5, 10, 15)) return
        viewModelScope.launch {
            userPreferencesRepository.setBlitzQuestionCount(count)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as SpaceEducationApplication
                SettingsViewModel(app.container.userPreferencesRepository)
            }
        }
    }
}