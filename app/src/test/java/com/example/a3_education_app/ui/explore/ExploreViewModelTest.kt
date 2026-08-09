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
    fun searchExoplanets_success_updatesStateToSuccess() {
        repository.shouldReturnError = false

        val viewModel = ExploreViewModel(repository)

        val state = viewModel.exploreUiState
        Assert.assertTrue(state is ExploreUiState.Success)
        val success = state as ExploreUiState.Success
        Assert.assertEquals(2, success.exoplanets.size)
        Assert.assertEquals("Kepler-22 b", success.exoplanets[0].pl_name)
    }

    @Test
    fun searchExoplanets_error_updatesStateToError() {
        repository.shouldReturnError = true

        val viewModel = ExploreViewModel(repository)

        Assert.assertEquals(ExploreUiState.Error, viewModel.exploreUiState)
    }

    @Test
    fun searchExoplanets_retryAfterError_updatesStateToSuccess() {
        repository.shouldReturnError = true
        val viewModel = ExploreViewModel(repository)
        Assert.assertEquals(ExploreUiState.Error, viewModel.exploreUiState)

        repository.shouldReturnError = false
        viewModel.searchExoplanets()

        Assert.assertTrue(viewModel.exploreUiState is ExploreUiState.Success)
    }

    @Test
    fun updateSearchText_updatesFilterState() {
        val viewModel = ExploreViewModel(repository)

        viewModel.updateSearchText("Kepler")

        Assert.assertEquals("Kepler", viewModel.filterState.searchText)
    }

    @Test
    fun updateResultLimit_updatesFilterStateAndQueryContainsTopLimit() {
        val viewModel = ExploreViewModel(repository)

        viewModel.updateResultLimit(200)

        Assert.assertEquals(200, viewModel.filterState.resultLimit)
        Assert.assertNotNull(repository.lastQuery)
        Assert.assertTrue(
            repository.lastQuery!!.contains("top 200") ||
                    repository.lastQuery!!.contains("top%20200")
        )
    }

    @Test
    fun applyFilters_withYearAndRadius_putsConstraintsInQuery() {
        val viewModel = ExploreViewModel(repository)

        viewModel.updateMinYear("2015")
        viewModel.updateMaxYear("2020")
        viewModel.updateMinRadius("1")
        viewModel.updateMaxRadius("3")
        viewModel.applyFilters()

        val query = repository.lastQuery
        Assert.assertNotNull(query)
        Assert.assertTrue(
            query!!.contains("disc_year") || query.contains("disc_year".replace("_", "%5F"))
        )
        Assert.assertTrue(query.contains("2015") || query.contains("pl_rade"))
    }

    @Test
    fun clearFilters_resetsStateToDefaults() {
        val viewModel = ExploreViewModel(repository)

        viewModel.updateSearchText("Kepler")
        viewModel.updateResultLimit(100)
        viewModel.updateMinYear("2010")
        viewModel.clearFilters()

        Assert.assertEquals("", viewModel.filterState.searchText)
        Assert.assertEquals(50, viewModel.filterState.resultLimit)
        Assert.assertEquals("", viewModel.filterState.minYear)
        Assert.assertTrue(viewModel.exploreUiState is ExploreUiState.Success)
    }
}