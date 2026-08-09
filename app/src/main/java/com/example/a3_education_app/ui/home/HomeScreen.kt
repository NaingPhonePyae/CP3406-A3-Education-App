package com.example.a3_education_app.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a3_education_app.R
import com.example.a3_education_app.ui.theme.A3EducationAppTheme

@Composable
fun HomeScreen(
    onSolarSystemClicked: () -> Unit,
    onExoplanetsClicked: () -> Unit,
    onFavoritesClicked: () -> Unit,
    onLessonsClicked: () -> Unit,
    onQuizClicked: () -> Unit,
    onStatisticsClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.home_welcome_title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.home_welcome_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )

        HomeMenuCard(
            title = stringResource(R.string.solar_system),
            subtitle = stringResource(R.string.home_solar_subtitle),
            icon = Icons.Filled.Public,
            onClick = onSolarSystemClicked
        )
        HomeMenuCard(
            title = stringResource(R.string.exoplanets),
            subtitle = stringResource(R.string.home_exoplanets_subtitle),
            icon = Icons.Filled.TravelExplore,
            onClick = onExoplanetsClicked
        )
        HomeMenuCard(
            title = stringResource(R.string.favorites),
            subtitle = stringResource(R.string.home_favorites_subtitle),
            icon = Icons.Filled.Star,
            onClick = onFavoritesClicked
        )
        HomeMenuCard(
            title = stringResource(R.string.lessons),
            subtitle = stringResource(R.string.home_lessons_subtitle),
            icon = Icons.AutoMirrored.Filled.MenuBook,
            onClick = onLessonsClicked
        )
        HomeMenuCard(
            title = stringResource(R.string.quiz),
            subtitle = stringResource(R.string.home_quiz_subtitle),
            icon = Icons.Filled.Quiz,
            onClick = onQuizClicked
        )
        HomeMenuCard(
            title = stringResource(R.string.statistics),
            subtitle = stringResource(R.string.home_statistics_subtitle),
            icon = Icons.Filled.BarChart,
            onClick = onStatisticsClicked
        )
        HomeMenuCard(
            title = stringResource(R.string.settings),
            subtitle = stringResource(R.string.home_settings_subtitle),
            icon = Icons.Filled.Settings,
            onClick = onSettingsClicked
        )
    }
}

@Composable
private fun HomeMenuCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    A3EducationAppTheme(darkTheme = false) {
        HomeScreen({}, {}, {}, {}, {}, {}, {})
    }
}