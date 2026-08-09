package com.example.a3_education_app.model

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)