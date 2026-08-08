package com.example.a3_education_app.ui.solar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a3_education_app.R
import com.example.a3_education_app.data.SolarSystemDataSource

@Composable
fun SolarBodyDetailScreen(
    bodyId: String,
    modifier: Modifier = Modifier
) {
    val body = SolarSystemDataSource.bodies.first { it.id == bodyId }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(body.name, style = MaterialTheme.typography.headlineMedium)
        Text(body.type, modifier = Modifier.padding(top = 8.dp))
        Text(stringResource(R.string.moons_count, body.moons))
        Text(stringResource(R.string.gravity, body.gravity))
        Text(stringResource(R.string.mean_radius, body.meanRadiusKm))
        Text(body.summary, modifier = Modifier.padding(top = 12.dp))
    }
}