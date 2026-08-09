package com.example.a3_education_app.data

import org.junit.Assert
import org.junit.Test

class LessonDataSourceTest {

    @Test
    fun lessons_isNotEmpty() {
        Assert.assertTrue(LessonDataSource.lessons.isNotEmpty())
    }

    @Test
    fun lessons_haveUniqueIds() {
        val ids = LessonDataSource.lessons.map { it.id }
        Assert.assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun lessons_areNotJustSolarPlanetCopies() {
        val solarIds = SolarSystemDataSource.bodies.map { it.id }.toSet()
        val lessonIds = LessonDataSource.lessons.map { it.id }.toSet()
        // Topic lessons should not be exactly the same id set as planets
        Assert.assertNotEquals(solarIds, lessonIds)
    }

    @Test
    fun lessonQuizLinks_pointToExistingQuizzes_whenPresent() {
        LessonDataSource.lessons.forEach { lesson ->
            val quizId = lesson.quizPlanetId ?: return@forEach
            Assert.assertTrue(
                "Missing quiz for lesson=${lesson.id}, quizPlanetId=$quizId",
                QuizQuestionDataSource.quizzesByPlanetId.containsKey(quizId)
            )
        }
    }

    @Test
    fun lessonFor_returnsMatchingLesson() {
        val first = LessonDataSource.lessons.first()
        Assert.assertEquals(first, LessonDataSource.lessonFor(first.id))
    }
}