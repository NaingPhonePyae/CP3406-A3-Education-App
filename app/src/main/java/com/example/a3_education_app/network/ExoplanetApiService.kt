package com.example.a3_education_app.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ExoplanetApiService {
    @GET("TAP/sync")
    suspend fun getExoplanets(
        @Query("query") query: String =
            "select top 20 pl_name,hostname,disc_year,pl_bmasse,pl_rade,sy_dist from pscomppars order by disc_year desc",
        @Query("format") format: String = "json"
    ): List<Exoplanet>
}