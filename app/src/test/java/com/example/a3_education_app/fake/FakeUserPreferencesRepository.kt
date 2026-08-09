package com.example.a3_education_app.fake

import com.example.a3_education_app.data.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FakeUserPreferencesRepository : UserPreferencesRepository {
    private val scores = MutableStateFlow<Map<String, Int>>(emptyMap())
    private val darkTheme = MutableStateFlow(false)

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
}