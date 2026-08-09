package com.example.a3_education_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_exoplanets")
data class FavoriteExoplanet(
    @PrimaryKey val plName: String,
    val hostname: String?,
    val discYear: Int?,
    val plBmasse: Double?,
    val plRade: Double?,
    val syDist: Double?
)