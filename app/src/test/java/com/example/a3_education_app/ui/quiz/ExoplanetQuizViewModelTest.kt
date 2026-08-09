package com.example.a3_education_app.ui.quiz

import com.example.a3_education_app.MainDispatcherRule
import com.example.a3_education_app.fake.FakeExoplanetRepository
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ExoplanetQuizViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeExoplanetRepository()

    @Test
    fun loadQuiz_success_setsReadyAndFiveQuestions() {
        repository.shouldReturnError = false

        val vm = ExoplanetQuizViewModel(repository)

        Assert.assertEquals(ExoplanetQuizLoadState.Ready, vm.loadState)
        Assert.assertEquals(5, vm.questionCount())
    }

    @Test
    fun loadQuiz_error_setsErrorState() {
        repository.shouldReturnError = true

        val vm = ExoplanetQuizViewModel(repository)

        Assert.assertEquals(ExoplanetQuizLoadState.Error, vm.loadState)
        Assert.assertEquals(0, vm.questionCount())
    }
}