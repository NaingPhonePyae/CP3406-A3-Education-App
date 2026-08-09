package com.example.a3_education_app.ui.quiz

import androidx.lifecycle.ViewModel
import com.example.a3_education_app.data.QuizQuestionDataSource
import com.example.a3_education_app.model.QuizQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel(planetId: String) : ViewModel() {

    private val questions: List<QuizQuestion> = QuizQuestionDataSource.questionsFor(planetId)

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

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
            _uiState.update { it.copy(isGameOver = true) }
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