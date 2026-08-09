package com.example.a3_education_app.fake

import com.example.a3_education_app.data.FavoriteExoplanet
import com.example.a3_education_app.data.FavoritesRepository
import com.example.a3_education_app.network.Exoplanet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FakeFavoritesRepository : FavoritesRepository {
    private val favorites = MutableStateFlow<List<FavoriteExoplanet>>(emptyList())

    override fun getAllFavorites(): Flow<List<FavoriteExoplanet>> = favorites

    override fun isFavorite(plName: String): Flow<Boolean> =
        favorites.map { list -> list.any { it.plName == plName } }

    override suspend fun addFavorite(exoplanet: Exoplanet) {
        val item = FavoriteExoplanet(
            plName = exoplanet.pl_name,
            hostname = exoplanet.hostname,
            discYear = exoplanet.disc_year,
            plBmasse = exoplanet.pl_bmasse,
            plRade = exoplanet.pl_rade,
            syDist = exoplanet.sy_dist
        )
        if (favorites.value.none { it.plName == item.plName }) {
            favorites.value = favorites.value + item
        }
    }

    override suspend fun removeFavorite(plName: String) {
        favorites.value = favorites.value.filterNot { it.plName == plName }
    }
}