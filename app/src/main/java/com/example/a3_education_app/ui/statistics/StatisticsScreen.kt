package com.example.a3_education_app.ui.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

@Composable
fun StatisticsScreen(
    modifier: Modifier = Modifier,
    viewModel: StatisticsViewModel = viewModel(factory = StatisticsViewModel.Factory)
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(
                R.string.stats_lessons,
                state.lessonsCompleted,
                state.totalLessons
            ),
            style = MaterialTheme.typography.titleMedium
        )
        LinearProgressIndicator(
            progress = {
                if (state.totalLessons == 0) 0f
                else state.lessonsCompleted / state.totalLessons.toFloat()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 20.dp)
        )

        Text(
            text = stringResource(
                R.string.stats_quizzes,
                state.quizzesCompleted,
                state.totalQuizzes
            ),
            style = MaterialTheme.typography.titleMedium
        )
        LinearProgressIndicator(
            progress = {
                if (state.totalQuizzes == 0) 0f
                else state.quizzesCompleted / state.totalQuizzes.toFloat()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 20.dp)
        )

        Text(
            text = stringResource(R.string.stats_favorites, state.favoritesCount),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = stringResource(R.string.stats_best_scores),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        state.bestScores.forEach { row ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = stringResource(
                        R.string.stats_score_line,
                        row.title,
                        row.bestScore
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(14.dp)
                )
            }
        }
    }
}