package com.example.a3_education_app.ui.solar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a3_education_app.R
import com.example.a3_education_app.data.SolarSystemDataSource
import com.example.a3_education_app.ui.theme.A3EducationAppTheme

@Composable
fun SolarSystemScreen(
    onBodyClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(SolarSystemDataSource.bodies, key = { it.id }) { body ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onBodyClicked(body.id) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(body.name, style = MaterialTheme.typography.titleMedium)
                    Text(body.type, style = MaterialTheme.typography.bodyMedium)
                    Text(stringResource(R.string.moons_count, body.moons))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SolarSystemScreenPreview() {
    A3EducationAppTheme {
        SolarSystemScreen(onBodyClicked = {})
    }
}