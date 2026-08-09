package com.example.a3_education_app.ui.favorites

import com.example.a3_education_app.MainDispatcherRule
import com.example.a3_education_app.fake.FakeFavoritesRepository
import com.example.a3_education_app.network.Exoplanet
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class FavoritesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun favorites_reflectRepository() = runTest {
        val repo = FakeFavoritesRepository()
        repo.addFavorite(
            Exoplanet("Kepler-22 b", "Kepler-22", 2011, 36.0, 2.4, 180.0)
        )

        val vm = FavoritesViewModel(repo)

        Assert.assertEquals(1, vm.favorites.value.size)
        Assert.assertEquals("Kepler-22 b", vm.favorites.value[0].plName)
    }
}