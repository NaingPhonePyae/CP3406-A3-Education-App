package com.example.a3_education_app.ui.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    when (val state = viewModel.exploreUiState) {
        is ExploreUiState.Loading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ExploreUiState.Error -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(stringResource(R.string.loading_failed))
                Button(
                    onClick = viewModel::getExoplanets,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(stringResource(R.string.retry))
                }
            }
        }

        is ExploreUiState.Success -> {
            ExoplanetList(
                exoplanets = state.exoplanets,
                onExoplanetClicked = onExoplanetClicked,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun ExoplanetList(
    exoplanets: List<Exoplanet>,
    onExoplanetClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
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
                    Text(
                        exoplanet.hostname ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    exoplanet.disc_year?.let {
                        Text(stringResource(R.string.discovery_year, it))
                    }
                }
            }
        }
    }
}