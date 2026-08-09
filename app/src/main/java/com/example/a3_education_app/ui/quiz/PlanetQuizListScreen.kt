package com.example.a3_education_app.ui.quiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a3_education_app.R
import com.example.a3_education_app.data.SolarSystemDataSource

@Composable
fun PlanetQuizListScreen(
    onPlanetQuizClicked: (String) -> Unit,
    onExoplanetBlitzClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlanetQuizListViewModel = viewModel(factory = PlanetQuizListViewModel.Factory)
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            val completedCount by viewModel.completedCount.collectAsState()
            val total = viewModel.totalQuizzes
            Text(stringResource(R.string.quiz_list_header), style = MaterialTheme.typography.titleLarge)
            Text(
                text = stringResource(R.string.quizzes_progress, completedCount, total),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            LinearProgressIndicator(
                progress = { if (total == 0) 0f else completedCount / total.toFloat() },
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            )
        }

        items(SolarSystemDataSource.bodies, key = { it.id }) { body ->
            val best by viewModel.highScoreFlow(body.id).collectAsState()
            val completed by viewModel.isCompletedFlow(body.id).collectAsState()
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onPlanetQuizClicked(body.id) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = stringResource(R.string.planet_quiz, body.name),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = stringResource(R.string.high_score, best),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = if (completed) {
                            stringResource(R.string.quiz_completed)
                        } else {
                            stringResource(R.string.quiz_not_completed)
                        },
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }

        item {
            val blitzBest by viewModel
                .highScoreFlow(ExoplanetQuizViewModel.QUIZ_ID)
                .collectAsState()
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable(onClick = onExoplanetBlitzClicked)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = stringResource(R.string.exoplanet_blitz),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = stringResource(R.string.high_score, blitzBest),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}