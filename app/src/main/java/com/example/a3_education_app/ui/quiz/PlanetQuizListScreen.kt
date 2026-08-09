package com.example.a3_education_app.ui.quiz

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a3_education_app.R
import com.example.a3_education_app.data.SolarSystemDataSource

@Composable
fun PlanetQuizListScreen(
    onPlanetQuizClicked: (String) -> Unit,
    onExoplanetBlitzClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(SolarSystemDataSource.bodies, key = { it.id }) { body ->
            Button(
                onClick = { onPlanetQuizClicked(body.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(stringResource(R.string.planet_quiz, body.name))
            }
        }

        item {
            Button(
                onClick = onExoplanetBlitzClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(stringResource(R.string.exoplanet_blitz))
            }
        }
    }
}