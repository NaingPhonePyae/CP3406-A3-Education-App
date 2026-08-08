package com.example.a3_education_app.data

import com.example.a3_education_app.model.QuizQuestion

object QuizQuestionDataSource {
    val questions = listOf(
        QuizQuestion(
            question = "Which planet is known as the Red Planet?",
            options = listOf("Venus", "Mars", "Jupiter", "Mercury"),
            correctAnswerIndex = 1
        ),
        QuizQuestion(
            question = "How many moons does Earth have?",
            options = listOf("0", "1", "2", "12"),
            correctAnswerIndex = 1
        ),
        QuizQuestion(
            question = "What is an exoplanet?",
            options = listOf(
                "A moon of Saturn",
                "A planet outside our solar system",
                "A dwarf planet",
                "A comet"
            ),
            correctAnswerIndex = 1
        )
    )
}