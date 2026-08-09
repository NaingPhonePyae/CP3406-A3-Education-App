package com.example.a3_education_app.fake

import com.example.a3_education_app.data.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FakeUserPreferencesRepository : UserPreferencesRepository {
    private val scores = MutableStateFlow<Map<String, Int>>(emptyMap())
    private val darkTheme = MutableStateFlow(false)
    private val lessonsCompleted = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    private val quizzesCompleted = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    private val blitzQuestionCount = MutableStateFlow(5)

    override fun highScoreFlow(quizId: String): Flow<Int> =
        scores.map { it[quizId] ?: 0 }

    override suspend fun updateHighScoreIfBetter(quizId: String, score: Int) {
        val current = scores.value[quizId] ?: 0
        if (score > current) {
            scores.value = scores.value + (quizId to score)
        }
    }

    override fun darkThemeFlow(): Flow<Boolean> = darkTheme

    override suspend fun setDarkTheme(enabled: Boolean) {
        darkTheme.value = enabled
    }

    override fun isLessonCompletedFlow(lessonId: String): Flow<Boolean> =
        lessonsCompleted.map { it[lessonId] ?: false }

    override suspend fun setLessonCompleted(lessonId: String, completed: Boolean) {
        lessonsCompleted.value = lessonsCompleted.value + (lessonId to completed)
    }

    override fun isQuizCompletedFlow(quizId: String): Flow<Boolean> =
        quizzesCompleted.map { it[quizId] ?: false }

    override suspend fun setQuizCompleted(quizId: String, completed: Boolean) {
        quizzesCompleted.value = quizzesCompleted.value + (quizId to completed)
    }

    override fun blitzQuestionCountFlow(): Flow<Int> = blitzQuestionCount

    override suspend fun setBlitzQuestionCount(count: Int) {
        blitzQuestionCount.value = count
    }
}