package com.example.a3_education_app.data

import com.example.a3_education_app.network.Exoplanet
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAllFavorites(): Flow<List<FavoriteExoplanet>>
    fun isFavorite(plName: String): Flow<Boolean>
    suspend fun addFavorite(exoplanet: Exoplanet)
    suspend fun removeFavorite(plName: String)
}

class OfflineFavoritesRepository(
    private val dao: FavoriteExoplanetDao
) : FavoritesRepository {
    override fun getAllFavorites(): Flow<List<FavoriteExoplanet>> = dao.getAllFavorites()
    override fun isFavorite(plName: String): Flow<Boolean> = dao.isFavorite(plName)

    override suspend fun addFavorite(exoplanet: Exoplanet) {
        dao.insert(
            FavoriteExoplanet(
                plName = exoplanet.pl_name,
                hostname = exoplanet.hostname,
                discYear = exoplanet.disc_year,
                plBmasse = exoplanet.pl_bmasse,
                plRade = exoplanet.pl_rade,
                syDist = exoplanet.sy_dist
            )
        )
    }

    override suspend fun removeFavorite(plName: String) {
        dao.deleteByName(plName)
    }
}