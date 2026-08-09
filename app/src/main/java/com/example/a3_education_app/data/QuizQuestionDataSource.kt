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
            ),
            QuizQuestion(
                question = "About how long does one day on Mercury last?",
                options = listOf("24 hours", "59 Earth days", "12 Earth days", "365 Earth days"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "How many moons does Mercury have?",
                options = listOf("0", "1", "2", "4"),
                correctAnswerIndex = 0
            ),
            QuizQuestion(
                question = "How many rings does Mercury have?",
                options = listOf("0", "1", "2", "4"),
                correctAnswerIndex = 0
            )
        ),

        "venus" to listOf(
            QuizQuestion(
                question = "Which is the hottest planet in the solar system?",
                options = listOf("Mercury", "Venus", "Mars", "Jupiter"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "Which planet is second from the Sun?",
                options = listOf("Mercury", "Venus", "Earth", "Mars"),
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
            ),
            QuizQuestion(
                question = "Which planet is similar to Earth in size and structure?",
                options = listOf("Mars", "Venus", "Jupiter", "Neptune"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "How many moons does Venus have?",
                options = listOf("0", "1", "2", "4"),
                correctAnswerIndex = 0
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
            ),
            QuizQuestion(
                question = "Which planet is known to support life?",
                options = listOf("Mars", "Venus", "Earth", "Jupiter"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                question = "Which planet is third from the Sun?",
                options = listOf("Venus", "Earth", "Mars", "Mercury"),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "What is Earth's natural satellite called?",
                options = listOf("Phobos", "Titan", "The Moon", "Europa"),
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
            ),
            QuizQuestion(
                question = "Approximately how large is Mars compared with Earth?",
                options = listOf(
                    "About twice the diameter",
                    "About the same diameter",
                    "About half the diameter",
                    "About one-tenth the diameter"
                ),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                question = "About how long is a day on Mars?",
                options = listOf(
                    "12 hours",
                    "24.6 hours",
                    "48 hours",
                    "365 days"
                ),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "What are the two moons of Mars called?",
                options = listOf(
                    "Titan and Europa",
                    "Phobos and Deimos",
                    "Io and Ganymede",
                    "Triton and Charon"
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
            ),
            QuizQuestion(
                question = "What type of planet is Jupiter?",
                options = listOf(
                    "Rocky planet",
                    "Gas giant",
                    "Ice giant",
                    "Dwarf planet"
                ),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "What are Jupiter's main atmospheric components?",
                options = listOf(
                    "Oxygen and nitrogen",
                    "Hydrogen and helium",
                    "Carbon dioxide and oxygen",
                    "Methane and oxygen"
                ),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "Does Jupiter have a ring system?",
                options = listOf(
                    "No",
                    "Yes, a faint ring system",
                    "Only one solid ring",
                    "Only liquid rings"
                ),
                correctAnswerIndex = 1
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
            ),
            QuizQuestion(
                question = "Which planet is the second-largest in the solar system?",
                options = listOf(
                    "Jupiter",
                    "Saturn",
                    "Uranus",
                    "Neptune"
                ),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "What type of planet is Saturn?",
                options = listOf(
                    "Rocky planet",
                    "Gas giant",
                    "Ice giant",
                    "Dwarf planet"
                ),
                correctAnswerIndex = 1
            ),
            QuizQuestion(
                question = "Which planet is the least dense in the solar system?",
                options = listOf(
                    "Jupiter",
                    "Saturn",
                    "Uranus",
                    "Neptune"
                ),
                correctAnswerIndex = 1
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
            ),
            QuizQuestion(
                question = "How many faint rings does Uranus have?",
                options = listOf("5", "8", "13", "20"),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                question = "What type of planet is Uranus?",
                options = listOf(
                    "Rocky planet",
                    "Gas giant",
                    "Ice giant",
                    "Dwarf planet"
                ),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                question = "Which planet is seventh from the Sun?",
                options = listOf(
                    "Saturn",
                    "Uranus",
                    "Neptune",
                    "Jupiter"
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
            ),
            QuizQuestion(
                question = "What type of planet is Neptune?",
                options = listOf(
                    "Rocky planet",
                    "Gas giant",
                    "Ice giant",
                    "Dwarf planet"
                ),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                question = "Which planet is eighth from the Sun?",
                options = listOf(
                    "Saturn",
                    "Uranus",
                    "Neptune",
                    "Jupiter"
                ),
                correctAnswerIndex = 2
            ),
            QuizQuestion(
                question = "How much farther from the Sun is Neptune than Earth?",
                options = listOf(
                    "About 5 times farther",
                    "About 10 times farther",
                    "More than 30 times farther",
                    "About 100 times farther"
                ),
                correctAnswerIndex = 2
            )
        )
    )

    fun questionsFor(planetId: String): List<QuizQuestion> =
        quizzesByPlanetId[planetId]
            ?: error("No quiz for planetId=$planetId")
}