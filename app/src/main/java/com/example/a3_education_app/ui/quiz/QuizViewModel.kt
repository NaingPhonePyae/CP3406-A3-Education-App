package com.example.a3_education_app.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3_education_app.data.QuizQuestionDataSource
import com.example.a3_education_app.data.UserPreferencesRepository
import com.example.a3_education_app.model.QuizQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel(
    private val planetId: String,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val questions: List<QuizQuestion> =
        QuizQuestionDataSource.questionsFor(planetId)

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    val highScore: StateFlow<Int> =
        userPreferencesRepository.highScoreFlow(planetId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    fun currentQuestion(): QuizQuestion = questions[_uiState.value.currentQuestionIndex]

    fun questionCount(): Int = questions.size

    fun selectAnswer(index: Int) {
        val state = _uiState.value
        if (state.selectedAnswerIndex != null || state.isGameOver) return

        val correct = index == currentQuestion().correctAnswerIndex
        _uiState.update {
            it.copy(
                selectedAnswerIndex = index,
                score = if (correct) it.score + 1 else it.score
            )
        }
    }

    fun nextQuestion() {
        val nextIndex = _uiState.value.currentQuestionIndex + 1
        if (nextIndex >= questions.size) {
            val finalScore = _uiState.value.score
            _uiState.update { it.copy(isGameOver = true) }
            viewModelScope.launch {
                userPreferencesRepository.updateHighScoreIfBetter(planetId, finalScore)
                userPreferencesRepository.setQuizCompleted(planetId)
            }
        } else {
            _uiState.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    selectedAnswerIndex = null
                )
            }
        }
    }

    fun resetQuiz() {
        _uiState.value = QuizUiState()
    }
}