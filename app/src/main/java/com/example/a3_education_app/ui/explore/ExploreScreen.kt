package com.example.a3_education_app.ui.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a3_education_app.R
import com.example.a3_education_app.network.Exoplanet
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ExploreScreen(
    onExoplanetClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = viewModel(factory = ExploreViewModel.Factory)
) {
    Column(modifier = modifier.fillMaxSize()) {
        ExoplanetSearchFilters(
            filterState = viewModel.filterState,
            onSearchChange = viewModel::updateSearchText,
            onMinYearChange = viewModel::updateMinYear,
            onMaxYearChange = viewModel::updateMaxYear,
            onMinRadiusChange = viewModel::updateMinRadius,
            onMaxRadiusChange = viewModel::updateMaxRadius,
            onResultLimitChange = viewModel::updateResultLimit,
            onApply = viewModel::applyFilters,
            onClear = viewModel::clearFilters
        )

        when (val state = viewModel.exploreUiState) {
            is ExploreUiState.Loading -> {
                Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is ExploreUiState.Error -> {
                Column(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(R.string.loading_failed))
                    Button(onClick = viewModel::searchExoplanets) {
                        Text(stringResource(R.string.retry))
                    }
                }
            }
            is ExploreUiState.Success -> {
                ExoplanetList(
                    exoplanets = state.exoplanets,
                    onExoplanetClicked = onExoplanetClicked,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ExoplanetSearchFilters(
    filterState: ExoplanetFilterState,
    onSearchChange: (String) -> Unit,
    onMinYearChange: (String) -> Unit,
    onMaxYearChange: (String) -> Unit,
    onMinRadiusChange: (String) -> Unit,
    onMaxRadiusChange: (String) -> Unit,
    onResultLimitChange: (Int) -> Unit,
    onApply: () -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = filterState.searchText,
            onValueChange = onSearchChange,
            label = { Text("Search name or host star") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = filterState.minYear,
                onValueChange = onMinYearChange,
                label = { Text("Min year") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = filterState.maxYear,
                onValueChange = onMaxYearChange,
                label = { Text("Max year") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = filterState.minRadius,
                onValueChange = onMinRadiusChange,
                label = { Text("Min radius") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = filterState.maxRadius,
                onValueChange = onMaxRadiusChange,
                label = { Text("Max radius") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }
        Text(
            text = "Results",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(50, 100, 200).forEach { limit ->
                val selected = filterState.resultLimit == limit
                if (selected) {
                    Button(
                        onClick = { onResultLimitChange(limit) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("$limit")
                    }
                } else {
                    OutlinedButton(
                        onClick = { onResultLimitChange(limit) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("$limit")
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = onApply, modifier = Modifier.weight(1f)) {
                Text("Apply filters")
            }
            TextButton(onClick = onClear, modifier = Modifier.weight(1f)) {
                Text("Clear")
            }
        }
    }
}

@Composable
private fun ExoplanetList(
    exoplanets: List<Exoplanet>,
    onExoplanetClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (exoplanets.isEmpty()) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No exoplanets match your search/filters.")
        }
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(exoplanets, key = { it.pl_name }) { exoplanet ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val encoded = URLEncoder.encode(
                            exoplanet.pl_name,
                            StandardCharsets.UTF_8.toString()
                        )
                        onExoplanetClicked(encoded)
                    }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(exoplanet.pl_name, style = MaterialTheme.typography.titleMedium)
                    Text(exoplanet.hostname ?: "")
                    exoplanet.disc_year?.let { Text("Discovered: $it") }
                    exoplanet.pl_rade?.let { Text("Radius: $it Earth radii") }
                }
            }
        }
    }
}