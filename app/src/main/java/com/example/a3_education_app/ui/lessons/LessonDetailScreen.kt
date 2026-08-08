package com.example.a3_education_app.ui.lessons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a3_education_app.data.LessonDataSource

@Composable
fun LessonDetailScreen(
    lessonId: Int,
    modifier: Modifier = Modifier
) {
    val lesson = LessonDataSource.lessons.first { it.id == lessonId }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(lesson.title, style = MaterialTheme.typography.headlineSmall)
        Text(lesson.content, modifier = Modifier.padding(top = 12.dp))
    }
}