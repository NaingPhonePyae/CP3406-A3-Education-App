package com.example.a3_education_app.data

import org.junit.Assert
import org.junit.Test

class QuizQuestionDataSourceTest {

    @Test
    fun questions_isNotEmpty() {
        Assert.assertTrue(QuizQuestionDataSource.questions.isNotEmpty())
    }

    @Test
    fun questions_correctAnswerIndexInRange() {
        QuizQuestionDataSource.questions.forEach { question ->
            Assert.assertTrue(question.options.isNotEmpty())
            Assert.assertTrue(question.correctAnswerIndex in question.options.indices)
        }
    }
}