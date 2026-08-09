package com.example.a3_education_app.ui.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a3_education_app.SpaceEducationApplication
import com.example.a3_education_app.data.FavoritesRepository
import com.example.a3_education_app.data.LessonDataSource
import com.example.a3_education_app.data.SolarSystemDataSource
import com.example.a3_education_app.data.UserPreferencesRepository
import com.example.a3_education_app.ui.quiz.ExoplanetQuizViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class QuizScoreRow(
    val quizId: String,
    val title: String,
    val bestScore: Int
)

data class StatisticsUiState(
    val lessonsCompleted: Int = 0,
    val totalLessons: Int = 0,
    val quizzesCompleted: Int = 0,
    val totalQuizzes: Int = 0,
    val favoritesCount: Int = 0,
    val bestScores: List<QuizScoreRow> = emptyList()
)

class StatisticsViewModel(
    userPreferencesRepository: UserPreferencesRepository,
    favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val lessonIds = LessonDataSource.lessons.map { it.id }
    private val quizRows = SolarSystemDataSource.bodies.map {
        it.id to it.name
    } + (ExoplanetQuizViewModel.QUIZ_ID to "Exoplanet Blitz")

    private val lessonsCompletedFlow = combine(
        lessonIds.map { userPreferencesRepository.isLessonCompletedFlow(it) }
    ) { values -> values.count { it } }

    private val quizzesCompletedFlow = combine(
        quizRows.map { (id, _) -> userPreferencesRepository.isQuizCompletedFlow(id) }
    ) { values -> values.count { it } }

    private val bestScoresFlow = combine(
        quizRows.map { (id, title) ->
            userPreferencesRepository.highScoreFlow(id).map { score ->
                QuizScoreRow(id, title, score)
            }
        }
    ) { rows -> rows.toList() }

    private val favoritesCountFlow =
        favoritesRepository.getAllFavorites().map { it.size }

    val uiState: StateFlow<StatisticsUiState> = combine(
        lessonsCompletedFlow,
        quizzesCompletedFlow,
        favoritesCountFlow,
        bestScoresFlow
    ) { lessonsDone, quizzesDone, favorites, scores ->
        StatisticsUiState(
            lessonsCompleted = lessonsDone,
            totalLessons = lessonIds.size,
            quizzesCompleted = quizzesDone,
            totalQuizzes = quizRows.size,
            favoritesCount = favorites,
            bestScores = scores
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), StatisticsUiState(
        totalLessons = lessonIds.size,
        totalQuizzes = quizRows.size
    ))

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as SpaceEducationApplication
                StatisticsViewModel(
                    userPreferencesRepository = app.container.userPreferencesRepository,
                    favoritesRepository = app.container.favoritesRepository
                )
            }
        }
    }
}