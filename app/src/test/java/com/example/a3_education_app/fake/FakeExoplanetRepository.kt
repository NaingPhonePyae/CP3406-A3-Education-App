package com.example.a3_education_app.fake

import com.example.a3_education_app.data.ExoplanetRepository
import com.example.a3_education_app.network.Exoplanet
import java.io.IOException

class FakeExoplanetRepository : ExoplanetRepository {

    var shouldReturnError: Boolean = false

    var lastQuery: String? = null
        private set

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
        ),
        Exoplanet(
            pl_name = "Kepler-22 b",
            hostname = "Kepler-22",
            disc_year = 2011,
            pl_bmasse = 36.0,
            pl_rade = 2.38,
            sy_dist = 190.0
        ),

        Exoplanet(
            pl_name = "Proxima Centauri b",
            hostname = "Proxima Centauri",
            disc_year = 2016,
            pl_bmasse = 1.27,
            pl_rade = 1.1,
            sy_dist = 1.3
        ),

        Exoplanet(
            pl_name = "TRAPPIST-1 e",
            hostname = "TRAPPIST-1",
            disc_year = 2017,
            pl_bmasse = 0.692,
            pl_rade = 0.92,
            sy_dist = 12.1
        ),

        Exoplanet(
            pl_name = "HD 209458 b",
            hostname = "HD 209458",
            disc_year = 1999,
            pl_bmasse = 219.0,
            pl_rade = 15.5,
            sy_dist = 47.0
        ),

        Exoplanet(
            pl_name = "51 Pegasi b",
            hostname = "51 Pegasi",
            disc_year = 1995,
            pl_bmasse = 150.0,
            pl_rade = 12.7,
            sy_dist = 15.6
        ),

        Exoplanet(
            pl_name = "Kepler-186 f",
            hostname = "Kepler-186",
            disc_year = 2014,
            pl_bmasse = null,
            pl_rade = 1.17,
            sy_dist = 151.0
        )

    )

    override suspend fun getExoplanets(query: String): List<Exoplanet> {
        lastQuery = query
        if (shouldReturnError) {
            throw IOException("Fake network error")
        }
        return sampleData
    }
}