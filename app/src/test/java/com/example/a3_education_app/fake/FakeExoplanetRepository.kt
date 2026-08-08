package com.example.a3_education_app.fake

import com.example.a3_education_app.data.ExoplanetRepository
import com.example.a3_education_app.network.Exoplanet
import java.io.IOException

class FakeExoplanetRepository : ExoplanetRepository {

    var shouldReturnError: Boolean = false

    private val sampleData = listOf(
        Exoplanet(
            pl_name = "Kepler-22 b",
            hostname = "Kepler-22",
            disc_year = 2011,
            pl_bmasse = 36.0,
            pl_rade = 2.4,
            sy_dist = 180.0
        ),
        Exoplanet(
            pl_name = "Proxima Centauri b",
            hostname = "Proxima Centauri",
            disc_year = 2016,
            pl_bmasse = 1.27,
            pl_rade = 1.1,
            sy_dist = 1.3
        )
    )

    override suspend fun getExoplanets(): List<Exoplanet> {
        if (shouldReturnError) {
            throw IOException("Fake network error")
        }
        return sampleData
    }
}