package com.example.a3_education_app.ui.lessons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a3_education_app.R
import com.example.a3_education_app.data.LessonDataSource

@Composable
fun LessonDetailScreen(
    lessonId: String,
    onTakeQuizClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LessonDetailViewModel = viewModel(factory = LessonDetailViewModel.factory(lessonId))
) {
    val lesson = LessonDataSource.lessonFor(lessonId)
    val isCompleted by viewModel.isCompleted.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(lesson.imageRes),
            contentDescription = lesson.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = lesson.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = lesson.content,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = stringResource(R.string.interesting_facts),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
        )
        lesson.facts.forEach { fact ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "• $fact",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(14.dp)
                )
            }
        }

        Text(
            text = stringResource(R.string.source_nasa, lesson.sourceUrl),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 12.dp)
        )

        if (isCompleted) {
            Text(
                text = stringResource(R.string.lesson_completed),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 20.dp)
            )
        } else {
            OutlinedButton(
                onClick = viewModel::markComplete,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(stringResource(R.string.mark_lesson_complete))
            }
        }

        lesson.quizPlanetId?.let { planetId ->
            Button(
                onClick = { onTakeQuizClicked(planetId) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(stringResource(R.string.take_quiz))
            }
        }
    }
}