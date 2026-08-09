package com.example.a3_education_app.ui.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a3_education_app.R
import com.example.a3_education_app.SpaceEducationApplication

@Composable
fun QuizScreen(
    planetId: String,
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = viewModel(
        key = planetId,
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val app = extras[APPLICATION_KEY] as SpaceEducationApplication
                return QuizViewModel(
                    planetId = planetId,
                    userPreferencesRepository = app.container.userPreferencesRepository
                ) as T
            }
        }
    )
) {
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