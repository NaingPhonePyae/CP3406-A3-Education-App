package com.example.a3_education_app.ui.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a3_education_app.R
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FavoritesScreen(
    onFavoriteClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = viewModel(factory = FavoritesViewModel.Factory)
) {
    val favorites by viewModel.favorites.collectAsState()

    if (favorites.isEmpty()) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.no_favorites),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(R.string.no_favorites_hint),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp, start = 24.dp, end = 24.dp)
                )
            }
        }
        return
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.favorites_header),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
        items(favorites, key = { it.plName }) { favorite ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        val encoded = URLEncoder.encode(
                            favorite.plName,
                            StandardCharsets.UTF_8.toString()
                        )
                        onFavoriteClicked(encoded)
                    }
            ) {
                Column(modifier.padding(16.dp)) {
                    Text(favorite.plName, style = MaterialTheme.typography.titleLarge)
                    favorite.hostname?.let {
                        Text(
                            text = stringResource(R.string.host_star, it),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    favorite.discYear?.let {
                        Text(stringResource(R.string.discovery_year, it))
                    }
                }
            }
        }
    }
}