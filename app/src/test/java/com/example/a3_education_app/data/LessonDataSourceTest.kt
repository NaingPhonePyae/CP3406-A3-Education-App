package com.example.a3_education_app.data

import org.junit.Assert
import org.junit.Test

class LessonDataSourceTest {
    @Test
    fun lessons_matchSolarBodies() {
        Assert.assertEquals(
            SolarSystemDataSource.bodies.map { it.id },
            LessonDataSource.lessons.map { it.id }
        )
    }

    @Test
    fun everyLesson_hasMatchingQuiz() {
        LessonDataSource.lessons.forEach { lesson ->
            Assert.assertTrue(
                QuizQuestionDataSource.quizzesByPlanetId.containsKey(lesson.quizPlanetId)
            )
        }
    }
}