package com.example.a3_education_app.model


data class Lesson(
    val id: String,
    val title: String,
    val content: String,
    val facts: List<String>,
    val imageRes: Int,
    val sourceUrl: String,
    val quizPlanetId: String? = null  // only show Take quiz if quizPlanetId != null
)