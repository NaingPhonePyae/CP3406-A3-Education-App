package com.example.a3_education_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteExoplanetDao {
    @Query("SELECT * FROM favorite_exoplanets ORDER BY plName ASC")
    fun getAllFavorites(): Flow<List<FavoriteExoplanet>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_exoplanets WHERE plName = :plName)")
    fun isFavorite(plName: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteExoplanet)

    @Query("DELETE FROM favorite_exoplanets WHERE plName = :plName")
    suspend fun deleteByName(plName: String)
}