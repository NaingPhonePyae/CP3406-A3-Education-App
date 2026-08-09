package com.example.a3_education_app.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a3_education_app.SpaceEducationApplication
import com.example.a3_education_app.data.FavoriteExoplanet
import com.example.a3_education_app.data.FavoritesRepository
import com.example.a3_education_app.network.Exoplanet
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExoplanetDetailViewModel(
    private val plName: String,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val isFavorite: StateFlow<Boolean> =
        favoritesRepository.isFavorite(plName)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    /** Used when opening detail from Favorites (Explore list may be empty). */
    val favoriteFromDb: StateFlow<FavoriteExoplanet?> =
        favoritesRepository.getAllFavorites()
            .map { list -> list.firstOrNull { it.plName == plName } }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    fun toggleFavorite(exoplanet: Exoplanet) {
        viewModelScope.launch {
            if (isFavorite.value) {
                favoritesRepository.removeFavorite(exoplanet.pl_name)
            } else {
                favoritesRepository.addFavorite(exoplanet)
            }
        }
    }

    companion object {
        fun factory(plName: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as SpaceEducationApplication
                ExoplanetDetailViewModel(
                    plName = plName,
                    favoritesRepository = app.container.favoritesRepository
                )
            }
        }
    }
}