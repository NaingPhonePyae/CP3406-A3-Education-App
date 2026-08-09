package com.example.a3_education_app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserPreferencesRepository {
    fun highScoreFlow(quizId: String): Flow<Int>
    suspend fun updateHighScoreIfBetter(quizId: String, score: Int)
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

    companion object {
        fun create(context: Context): UserPreferencesRepository {
            return UserPreferencesRepositoryImpl(context.dataStore)
        }
    }
}