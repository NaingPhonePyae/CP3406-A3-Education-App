package com.example.a3_education_app.network

import kotlinx.serialization.Serializable

@Serializable
data class Exoplanet(
    val pl_name: String,
    val hostname: String? = null,
    val disc_year: Int? = null,
    val pl_bmasse: Double? = null,
    val pl_rade: Double? = null,
    val sy_dist: Double? = null
)