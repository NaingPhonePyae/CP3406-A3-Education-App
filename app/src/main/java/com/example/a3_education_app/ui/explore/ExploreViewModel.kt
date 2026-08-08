package com.example.a3_education_app.ui.explore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a3_education_app.SpaceEducationApplication
import com.example.a3_education_app.data.ExoplanetRepository
import com.example.a3_education_app.network.Exoplanet
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface ExploreUiState {
    data class Success(val exoplanets: List<Exoplanet>) : ExploreUiState
    object Error : ExploreUiState
    object Loading : ExploreUiState
}

class ExploreViewModel(
    private val exoplanetRepository: ExoplanetRepository
) : ViewModel() {

    var exploreUiState: ExploreUiState by mutableStateOf(ExploreUiState.Loading)
        private set

    init {
        getExoplanets()
    }

    fun getExoplanets() {
        viewModelScope.launch {
            exploreUiState = ExploreUiState.Loading
            exploreUiState = try {
                ExploreUiState.Success(exoplanetRepository.getExoplanets())
            } catch (e: IOException) {
                ExploreUiState.Error
            } catch (e: HttpException) {
                ExploreUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as SpaceEducationApplication
                ExploreViewModel(
                    exoplanetRepository = application.container.exoplanetRepository
                )
            }
        }
    }
}