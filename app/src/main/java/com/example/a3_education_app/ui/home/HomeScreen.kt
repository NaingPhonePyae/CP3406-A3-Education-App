package com.example.a3_education_app.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onSolarSystemClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(R.string.solar_system))
        }
        Button(
            onClick = onExoplanetsClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(R.string.exoplanets))
        }
        Button(
            onClick = onFavoritesClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(R.string.favorites))
        }
        Button(
            onClick = onLessonsClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(R.string.lessons))
        }
        Button(
            onClick = onQuizClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(stringResource(R.string.quiz))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    A3EducationAppTheme {
        HomeScreen({}, {}, {}, {}, {})
    }
}