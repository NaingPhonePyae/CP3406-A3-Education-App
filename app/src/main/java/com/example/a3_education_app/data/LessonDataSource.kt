package com.example.a3_education_app.data

import com.example.a3_education_app.model.Lesson

object LessonDataSource {
    val lessons: List<Lesson> = SolarSystemDataSource.bodies.map { body ->
        Lesson(
            id = body.id,
            title = body.name,
            content = body.summary,
            facts = body.facts,
            imageRes = body.imageRes,
            sourceUrl = body.sourceUrl,
            quizPlanetId = body.id
        )
    }

    fun lessonFor(id: String): Lesson =
        lessons.first { it.id == id }
}