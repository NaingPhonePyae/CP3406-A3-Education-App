package com.example.a3_education_app.ui.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a3_education_app.R

@Composable
fun ExoplanetQuizScreen(
    modifier: Modifier = Modifier,
    viewModel: ExoplanetQuizViewModel = viewModel(factory = ExoplanetQuizViewModel.Factory)
) {
    when (viewModel.loadState) {
        ExoplanetQuizLoadState.Loading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        ExoplanetQuizLoadState.Error -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(stringResource(R.string.loading_failed))
                Button(
                    onClick = viewModel::loadQuiz,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(stringResource(R.string.retry))
                }
            }
        }

        ExoplanetQuizLoadState.Ready -> {
            val uiState by viewModel.uiState.collectAsState()

            if (uiState.isGameOver) {
                val highScore by viewModel.highScore.collectAsState()
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.high_score, highScore),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(R.string.score, uiState.score),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Button(
                        onClick = viewModel::resetQuiz,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(stringResource(R.string.play_again))
                    }
                }
                return
            }

            val question = viewModel.currentQuestion()

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    stringResource(
                        R.string.question_count,
                        uiState.currentQuestionIndex + 1,
                        viewModel.questionCount()
                    )
                )
                Text(stringResource(R.string.score, uiState.score))
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                question.options.forEachIndexed { index, option ->
                    Button(
                        onClick = { viewModel.selectAnswer(index) },
                        enabled = uiState.selectedAnswerIndex == null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(option)
                    }
                }
                if (uiState.selectedAnswerIndex != null) {
                    Button(
                        onClick = viewModel::nextQuestion,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(stringResource(R.string.next))
                    }
                }
            }
        }
    }
}