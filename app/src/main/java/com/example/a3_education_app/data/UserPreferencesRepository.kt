package com.example.a3_education_app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserPreferencesRepository {
    fun highScoreFlow(quizId: String): Flow<Int>
    suspend fun updateHighScoreIfBetter(quizId: String, score: Int)

    fun darkThemeFlow(): Flow<Boolean>
    suspend fun setDarkTheme(enabled: Boolean)
    fun isLessonCompletedFlow(lessonId: String): Flow<Boolean>
    suspend fun setLessonCompleted(lessonId: String, completed: Boolean)
    fun isQuizCompletedFlow(quizId: String): Flow<Boolean>
    suspend fun setQuizCompleted(quizId: String, completed: Boolean = true)
    fun blitzQuestionCountFlow(): Flow<Int>
    suspend fun setBlitzQuestionCount(count: Int)
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "space_education_prefs"
)

class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {

    override fun highScoreFlow(quizId: String): Flow<Int> {
        val key = intPreferencesKey("high_score_$quizId")
        return dataStore.data.map { prefs -> prefs[key] ?: 0 }
    }

    override suspend fun updateHighScoreIfBetter(quizId: String, score: Int) {
        val key = intPreferencesKey("high_score_$quizId")
        dataStore.edit { prefs ->
            val current = prefs[key] ?: 0
            if (score > current) {
                prefs[key] = score
            }
        }
    }

    override fun darkThemeFlow(): Flow<Boolean> {
        val key = booleanPreferencesKey("dark_theme")
        return dataStore.data.map { prefs -> prefs[key] ?: false }
    }

    override suspend fun setDarkTheme(enabled: Boolean) {
        val key = booleanPreferencesKey("dark_theme")
        dataStore.edit { prefs ->
            prefs[key] = enabled
        }
    }

    override fun isLessonCompletedFlow(lessonId: String): Flow<Boolean> {
        val key = booleanPreferencesKey("lesson_completed_$lessonId")
        return dataStore.data.map { prefs -> prefs[key] ?: false }
    }

    override suspend fun setLessonCompleted(lessonId: String, completed: Boolean) {
        val key = booleanPreferencesKey("lesson_completed_$lessonId")
        dataStore.edit { prefs ->
            prefs[key] = completed
        }
    }

    override fun isQuizCompletedFlow(quizId: String): Flow<Boolean> {
        val key = booleanPreferencesKey("quiz_completed_$quizId")
        return dataStore.data.map { prefs -> prefs[key] ?: false }
    }

    override suspend fun setQuizCompleted(quizId: String, completed: Boolean) {
        val key = booleanPreferencesKey("quiz_completed_$quizId")
        dataStore.edit { prefs ->
            prefs[key] = completed
        }
    }

    companion object {
        fun create(context: Context): UserPreferencesRepository {
            return UserPreferencesRepositoryImpl(context.dataStore)
        }
    }

    override fun blitzQuestionCountFlow(): Flow<Int> {
        val key = intPreferencesKey("blitz_question_count")
        return dataStore.data.map { prefs -> prefs[key] ?: 5 }
    }
    override suspend fun setBlitzQuestionCount(count: Int) {
        val key = intPreferencesKey("blitz_question_count")
        dataStore.edit { prefs -> prefs[key] = count }
    }

}