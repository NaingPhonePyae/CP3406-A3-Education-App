package com.example.a3_education_app.model


data class Lesson(
    val id: String,          // same as planet id: "mars"
    val title: String,
    val content: String,     // summary
    val facts: List<String>,
    val imageRes: Int,
    val sourceUrl: String,
    val quizPlanetId: String // usually same as id
)