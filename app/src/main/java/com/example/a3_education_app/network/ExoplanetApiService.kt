package com.example.a3_education_app.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ExoplanetApiService {
    @GET("TAP/sync")
    suspend fun getExoplanets(
        @Query("query", encoded = true) query: String,
        @Query("format") format: String = "json"
    ): List<Exoplanet>
}