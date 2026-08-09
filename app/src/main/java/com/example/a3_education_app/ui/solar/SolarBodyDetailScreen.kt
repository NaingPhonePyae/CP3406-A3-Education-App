package com.example.a3_education_app.ui.solar

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a3_education_app.R
import com.example.a3_education_app.data.SolarSystemDataSource

@Composable
fun SolarBodyDetailScreen(
    bodyId: String,
    onTakeQuizClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val body = SolarSystemDataSource.bodies.first { it.id == bodyId }

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(body.imageRes),
            contentDescription = body.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = body.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(body.summary, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 8.dp))
        Text(stringResource(R.string.moons_count, body.moons), modifier = Modifier.padding(top = 8.dp))
        Text(stringResource(R.string.gravity, body.gravity))
        Text(stringResource(R.string.mean_radius, body.meanRadiusKm))

        Text(
            text = stringResource(R.string.interesting_facts),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
        )
        body.facts.forEach { fact ->
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
            text = stringResource(R.string.source_nasa, body.sourceUrl),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 12.dp)
        )

        Button(
            onClick = { onTakeQuizClicked(body.id) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 8.dp)
        ) {
            Text(stringResource(R.string.take_quiz))
        }
    }
}