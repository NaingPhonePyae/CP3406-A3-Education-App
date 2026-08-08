package com.example.a3_education_app.ui.explore

import com.example.a3_education_app.MainDispatcherRule
import com.example.a3_education_app.fake.FakeExoplanetRepository
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ExploreViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeExoplanetRepository()

    @Test
    fun getExoplanets_success_updatesStateToSuccess() {
        repository.shouldReturnError = false

        val viewModel = ExploreViewModel(repository)

        val state = viewModel.exploreUiState
        Assert.assertTrue(state is ExploreUiState.Success)
        val success = state as ExploreUiState.Success
        Assert.assertEquals(2, success.exoplanets.size)
        Assert.assertEquals("Kepler-22 b", success.exoplanets[0].pl_name)
    }

    @Test
    fun getExoplanets_error_updatesStateToError() {
        repository.shouldReturnError = true

        val viewModel = ExploreViewModel(repository)

        Assert.assertEquals(ExploreUiState.Error, viewModel.exploreUiState)
    }

    @Test
    fun getExoplanets_retryAfterError_updatesStateToSuccess() {
        repository.shouldReturnError = true
        val viewModel = ExploreViewModel(repository)
        Assert.assertEquals(ExploreUiState.Error, viewModel.exploreUiState)

        repository.shouldReturnError = false
        viewModel.getExoplanets()

        Assert.assertTrue(viewModel.exploreUiState is ExploreUiState.Success)
    }
}