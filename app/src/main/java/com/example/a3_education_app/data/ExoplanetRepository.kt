package com.example.a3_education_app.data

import com.example.a3_education_app.network.Exoplanet
import com.example.a3_education_app.network.ExoplanetApiService

interface ExoplanetRepository {
    suspend fun getExoplanets(): List<Exoplanet>
}

class NetworkExoplanetRepository(
    private val exoplanetApiService: ExoplanetApiService
) : ExoplanetRepository {
    override suspend fun getExoplanets(): List<Exoplanet> =
        exoplanetApiService.getExoplanets()
}