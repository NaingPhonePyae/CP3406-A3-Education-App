package com.example.a3_education_app.ui.quiz

data class QuizUiState(
    val currentQuestionIndex: Int = 0,
    val score: Int = 0,
    val selectedAnswerIndex: Int? = null,
    val isGameOver: Boolean = false
)