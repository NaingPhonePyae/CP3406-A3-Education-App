package com.example.a3_education_app.ui.explore

import com.example.a3_education_app.MainDispatcherRule
import com.example.a3_education_app.fake.FakeFavoritesRepository
import com.example.a3_education_app.network.Exoplanet
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ExoplanetDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repo = FakeFavoritesRepository()
    private val sample = Exoplanet(
        pl_name = "Kepler-22 b",
        hostname = "Kepler-22",
        disc_year = 2011,
        pl_bmasse = 36.0,
        pl_rade = 2.4,
        sy_dist = 180.0
    )

    @Test
    fun toggleFavorite_addThenRemove() = runTest {
        val vm = ExoplanetDetailViewModel("Kepler-22 b", repo)

        Assert.assertFalse(vm.isFavorite.value)

        vm.toggleFavorite(sample)
        Assert.assertTrue(vm.isFavorite.value)

        vm.toggleFavorite(sample)
        Assert.assertFalse(vm.isFavorite.value)
    }
}