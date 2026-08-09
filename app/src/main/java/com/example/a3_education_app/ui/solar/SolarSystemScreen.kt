package com.example.a3_education_app.ui.solar

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
        item {
            Text(
                text = stringResource(R.string.solar_list_header),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
        items(SolarSystemDataSource.bodies, key = { it.id }) { body ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onBodyClicked(body.id) }
            ) {
                Column {
                    Image(
                        painter = painterResource(body.imageRes),
                        contentDescription = body.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(body.name, style = MaterialTheme.typography.titleLarge)
                        Text(body.summary, style = MaterialTheme.typography.bodyMedium)
                        Text(
                            text = stringResource(R.string.moons_count, body.moons),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SolarSystemScreenPreview() {
    A3EducationAppTheme(darkTheme = false) {
        SolarSystemScreen(onBodyClicked = {})
    }
}