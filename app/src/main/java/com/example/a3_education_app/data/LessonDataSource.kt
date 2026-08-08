package com.example.a3_education_app.data

import com.example.a3_education_app.model.Lesson

object LessonDataSource {
    val lessons = listOf(
        Lesson(
            1,
            "What is a planet?",
            "A planet orbits a star, is rounded by its own gravity, and has cleared its orbital path."
        ),
        Lesson(
            2,
            "Moons",
            "Moons are natural satellites that orbit planets. Earth has one moon; Jupiter has many."
        ),
        Lesson(
            3,
            "Exoplanets",
            "Exoplanets are planets outside our solar system. NASA catalogs thousands of them."
        )
    )
}