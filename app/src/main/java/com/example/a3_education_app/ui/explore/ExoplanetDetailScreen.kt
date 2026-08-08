package com.example.a3_education_app.ui.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a3_education_app.R
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun ExoplanetDetailScreen(
    encodedName: String,
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = viewModel(factory = ExploreViewModel.Factory)
) {
    val name = URLDecoder.decode(encodedName, StandardCharsets.UTF_8.toString())
    val state = viewModel.exploreUiState
    val exoplanet = (state as? ExploreUiState.Success)
        ?.exoplanets
        ?.firstOrNull { it.pl_name == name }

    if (exoplanet == null) {
        Text(
            text = stringResource(R.string.loading),
            modifier = modifier.padding(16.dp)
        )
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(exoplanet.pl_name, style = MaterialTheme.typography.headlineMedium)
        exoplanet.hostname?.let {
            Text(stringResource(R.string.host_star, it), modifier = Modifier.padding(top = 8.dp))
        }
        exoplanet.disc_year?.let {
            Text(stringResource(R.string.discovery_year, it))
        }
        exoplanet.pl_bmasse?.let {
            Text(stringResource(R.string.planet_mass, it))
        }
        exoplanet.pl_rade?.let {
            Text(stringResource(R.string.planet_radius, it))
        }
        exoplanet.sy_dist?.let {
            Text(stringResource(R.string.distance, it))
        }
    }
}