package com.example.a3_education_app.ui.quiz

import com.example.a3_education_app.data.QuizQuestionDataSource
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class QuizViewModelTest {

    private lateinit var viewModel: QuizViewModel

    @Before
    fun setup() {
        viewModel = QuizViewModel()
    }

    @Test
    fun initialization_setsDefaultState() {
        val state = viewModel.uiState.value
        Assert.assertEquals(0, state.currentQuestionIndex)
        Assert.assertEquals(0, state.score)
        Assert.assertEquals(null, state.selectedAnswerIndex)
        Assert.assertFalse(state.isGameOver)
    }

    @Test
    fun selectAnswer_correctAnswer_increasesScore() {
        val correctIndex = viewModel.currentQuestion().correctAnswerIndex

        viewModel.selectAnswer(correctIndex)

        Assert.assertEquals(1, viewModel.uiState.value.score)
        Assert.assertEquals(correctIndex, viewModel.uiState.value.selectedAnswerIndex)
    }

    @Test
    fun selectAnswer_wrongAnswer_doesNotIncreaseScore() {
        val correctIndex = viewModel.currentQuestion().correctAnswerIndex
        val wrongIndex = if (correctIndex == 0) 1 else 0

        viewModel.selectAnswer(wrongIndex)

        Assert.assertEquals(0, viewModel.uiState.value.score)
        Assert.assertEquals(wrongIndex, viewModel.uiState.value.selectedAnswerIndex)
    }

    @Test
    fun selectAnswer_secondTapIgnored() {
        val correctIndex = viewModel.currentQuestion().correctAnswerIndex
        viewModel.selectAnswer(correctIndex)
        viewModel.selectAnswer(0)

        Assert.assertEquals(correctIndex, viewModel.uiState.value.selectedAnswerIndex)
        Assert.assertEquals(1, viewModel.uiState.value.score)
    }

    @Test
    fun nextQuestion_advancesIndexAndClearsSelection() {
        viewModel.selectAnswer(viewModel.currentQuestion().correctAnswerIndex)
        viewModel.nextQuestion()

        val state = viewModel.uiState.value
        Assert.assertEquals(1, state.currentQuestionIndex)
        Assert.assertEquals(null, state.selectedAnswerIndex)
        Assert.assertFalse(state.isGameOver)
    }

    @Test
    fun nextQuestion_onLastQuestion_setsGameOver() {
        val lastIndex = QuizQuestionDataSource.questions.lastIndex

        repeat(QuizQuestionDataSource.questions.size) { index ->
            Assert.assertEquals(index, viewModel.uiState.value.currentQuestionIndex)
            viewModel.selectAnswer(viewModel.currentQuestion().correctAnswerIndex)
            viewModel.nextQuestion()
        }

        Assert.assertTrue(viewModel.uiState.value.isGameOver)
        Assert.assertEquals(lastIndex, viewModel.uiState.value.currentQuestionIndex)
        Assert.assertEquals(QuizQuestionDataSource.questions.size, viewModel.uiState.value.score)
    }

    @Test
    fun resetQuiz_restoresDefaultState() {
        viewModel.selectAnswer(viewModel.currentQuestion().correctAnswerIndex)
        viewModel.nextQuestion()

        viewModel.resetQuiz()

        val state = viewModel.uiState.value
        Assert.assertEquals(0, state.currentQuestionIndex)
        Assert.assertEquals(0, state.score)
        Assert.assertEquals(null, state.selectedAnswerIndex)
        Assert.assertFalse(state.isGameOver)
    }

    @Test
    fun questionCount_matchesDataSource() {
        Assert.assertEquals(
            QuizQuestionDataSource.questions.size,
            viewModel.questionCount()
        )
    }
}