package com.example.a3_education_app.ui.explore

data class ExoplanetFilterState(
    val searchText: String = "",
    val minYear: String = "",
    val maxYear: String = "",
    val minRadius: String = "",    // Earth radii
    val maxRadius: String = "",
    val resultLimit: Int = 50
)