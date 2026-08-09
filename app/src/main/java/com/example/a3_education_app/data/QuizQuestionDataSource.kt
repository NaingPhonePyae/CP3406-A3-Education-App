package com.example.a3_education_app.data

import com.example.a3_education_app.model.QuizQuestion

object QuizQuestionDataSource {

    val quizzesByPlanetId: Map<String, List<QuizQuestion>> = mapOf(

        "mercury" to listOf(
            QuizQuestion(
                question = "Which planet is the smallest in the solar system?",
                options = listOf("Mars", "Mercury", "Venus", "Earth"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "Which planet is closest to the Sun?",
                options = listOf("Venus", "Earth", "Mercury", "Mars"),
                correctAnswerIndex = 2
            )
        ),

        "venus" to listOf(
            QuizQuestion(
                question = "Which is the hottest planet in the solar system?",
                options = listOf("Mercury", "Venus", "Mars", "Jupiter"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "What is unusual about Venus's rotation?",
                options = listOf(
                    "It does not rotate",
                    "It rotates extremely fast",
                    "It rotates in the opposite direction from most planets",
                    "It rotates around the Sun twice"
                ),
                correctAnswerIndex = 2
            )
        ),

        "earth" to listOf(
            QuizQuestion(
                question = "How many natural satellites does Earth have?",
                options = listOf("0", "1", "2", "4"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "Approximately what percentage of Earth's surface is covered by water?",
                options = listOf("30%", "50%", "71%", "90%"),
                correctAnswerIndex = 2
            )
        ),

        "mars" to listOf(
            QuizQuestion(
                question = "How many moons does Mars have?",
                options = listOf("0", "1", "2", "4"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                question = "What is Olympus Mons?",
                options = listOf(
                    "A moon",
                    "A volcano",
                    "A ring",
                    "An ocean"
                ),
                correctAnswerIndex = 1
            )
        ),

        "jupiter" to listOf(
            QuizQuestion(
                question = "Which is the largest planet in the solar system?",
                options = listOf("Saturn", "Earth", "Jupiter", "Neptune"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                question = "What is the Great Red Spot on Jupiter?",
                options = listOf(
                    "A giant storm",
                    "A volcano",
                    "A moon",
                    "A mountain"
                ),
                correctAnswerIndex = 0
            )
        ),

        "saturn" to listOf(
            QuizQuestion(
                question = "What is Saturn best known for?",
                options = listOf(
                    "Its oceans",
                    "Its spectacular ring system",
                    "Being the hottest planet",
                    "Having no atmosphere"
                ),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "What are Saturn's rings made mostly of?",
                options = listOf(
                    "Gas and dust",
                    "Fire and lava",
                    "Ice and rock",
                    "Liquid water"
                ),
                correctAnswerIndex = 2
            )
        ),

        "uranus" to listOf(
            QuizQuestion(
                question = "Why does Uranus appear to rotate almost on its side?",
                options = listOf(
                    "It has an axial tilt of about 98 degrees",
                    "It has no gravity",
                    "It is shaped like an oval",
                    "It rotates around another planet"
                ),
                correctAnswerIndex = 0
            ),
            QuizQuestion(
                question = "What gives Uranus its blue-green color?",
                options = listOf(
                    "Water",
                    "Methane in its atmosphere",
                    "Its rings",
                    "Volcanic gases"
                ),
                correctAnswerIndex = 1
            )
        ),

        "neptune" to listOf(
            QuizQuestion(
                question = "Which planet is the most distant from the Sun?",
                options = listOf(
                    "Uranus",
                    "Saturn",
                    "Neptune",
                    "Jupiter"
                ),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                question = "What is notable about Neptune's winds?",
                options = listOf(
                    "They are the fastest winds in the solar system",
                    "Neptune has no winds",
                    "They only occur near the equator",
                    "They are slower than Earth's winds"
                ),
                correctAnswerIndex = 0
            )
        )
    )

    fun questionsFor(planetId: String): List<QuizQuestion> =
        quizzesByPlanetId[planetId]
            ?: error("No quiz for planetId=$planetId")
}