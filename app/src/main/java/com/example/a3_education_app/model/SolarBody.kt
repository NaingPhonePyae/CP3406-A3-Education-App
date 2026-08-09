package com.example.a3_education_app.model

data class SolarBody(
    val id: String,
    val name: String,
    val type: String,          // "Planet", "Moon", "Dwarf Planet"
    val moons: Int,
    val meanRadiusKm: Double,
    val gravity: Double,
    val summary: String,
    val facts: List<String>,
    val imageRes: Int,
    val sourceUrl: String
)