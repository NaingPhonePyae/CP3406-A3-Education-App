package com.example.a3_education_app.ui.explore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a3_education_app.SpaceEducationApplication
import com.example.a3_education_app.data.ExoplanetRepository
import com.example.a3_education_app.network.Exoplanet
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed interface ExploreUiState {
    data class Success(val exoplanets: List<Exoplanet>) : ExploreUiState
    object Error : ExploreUiState
    object Loading : ExploreUiState
}

class ExploreViewModel(
    private val exoplanetRepository: ExoplanetRepository
) : ViewModel() {

    var exploreUiState: ExploreUiState by mutableStateOf(ExploreUiState.Loading)
        private set

    var filterState by mutableStateOf(ExoplanetFilterState())
        private set

    private var searchJob: Job? = null

    init {
        searchExoplanets()
    }

    fun updateSearchText(text: String) {
        filterState = filterState.copy(searchText = text)
        // debounce so every keystroke doesn’t hit NASA
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(400)
            searchExoplanets()
        }
    }

    fun updateMinYear(value: String) {
        filterState = filterState.copy(minYear = value.filter { it.isDigit() })
    }

    fun updateMaxYear(value: String) {
        filterState = filterState.copy(maxYear = value.filter { it.isDigit() })
    }

    fun updateMinRadius(value: String) {
        filterState = filterState.copy(minRadius = value.filter { it.isDigit() || it == '.' })
    }

    fun updateMaxRadius(value: String) {
        filterState = filterState.copy(maxRadius = value.filter { it.isDigit() || it == '.' })
    }

    fun updateResultLimit(limit: Int) {
        filterState = filterState.copy(resultLimit = limit)
        searchExoplanets()
    }

    fun applyFilters() {
        searchExoplanets()
    }

    fun clearFilters() {
        filterState = ExoplanetFilterState()
        searchExoplanets()
    }

    fun searchExoplanets() {
        viewModelScope.launch {
            exploreUiState = ExploreUiState.Loading
            exploreUiState = try {
                val query = buildTapQuery(filterState)
                ExploreUiState.Success(exoplanetRepository.getExoplanets(query))
            } catch (e: IOException) {
                ExploreUiState.Error
            } catch (e: HttpException) {
                ExploreUiState.Error
            }
        }
    }

    private fun buildTapQuery(filters: ExoplanetFilterState): String {
        val where = mutableListOf<String>()

        val search = filters.searchText.trim()
        if (search.isNotEmpty()) {
            // escape single quotes for ADQL
            val safe = search.replace("'", "''")
            where += "(pl_name like '%$safe%' or hostname like '%$safe%')"
        }
        filters.minYear.toIntOrNull()?.let { where += "disc_year >= $it" }
        filters.maxYear.toIntOrNull()?.let { where += "disc_year <= $it" }
        filters.minRadius.toDoubleOrNull()?.let { where += "pl_rade >= $it" }
        filters.maxRadius.toDoubleOrNull()?.let { where += "pl_rade <= $it" }

        val whereClause = if (where.isEmpty()) {
            ""
        } else {
            " where " + where.joinToString(" and ")
        }

        val limit = filters.resultLimit.coerceIn(1, 500)

        val query =
            "select top $limit pl_name,hostname,disc_year,pl_bmasse,pl_rade,sy_dist " +
                    "from pscomppars$whereClause order by disc_year desc"

        // encode for Retrofit encoded=true query param
        return URLEncoder.encode(query, StandardCharsets.UTF_8.toString())
            .replace("+", "%20")
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as SpaceEducationApplication
                ExploreViewModel(application.container.exoplanetRepository)
            }
        }
    }
}