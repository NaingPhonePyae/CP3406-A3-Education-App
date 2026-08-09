package com.example.a3_education_app.ui.lessons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a3_education_app.R
import com.example.a3_education_app.data.LessonDataSource

@Composable
fun LessonsScreen(
    onLessonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LessonsViewModel = viewModel(factory = LessonsViewModel.Factory)
) {
    val completedCount by viewModel.completedCount.collectAsState()
    val total = viewModel.totalLessons

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.lessons_header),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.lessons_progress, completedCount, total),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            LinearProgressIndicator(
                progress = { if (total == 0) 0f else completedCount / total.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }
        items(LessonDataSource.lessons, key = { it.id }) { lesson ->
            val completed by viewModel.isCompletedFlow(lesson.id).collectAsState()
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onLessonClicked(lesson.id) }
            ) {
                Column {
                    Image(
                        painter = painterResource(lesson.imageRes),
                        contentDescription = lesson.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(lesson.title, style = MaterialTheme.typography.titleLarge)
                        Text(
                            text = if (completed) {
                                stringResource(R.string.lesson_completed)
                            } else {
                                stringResource(R.string.lesson_not_completed)
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (completed) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}