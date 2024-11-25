package com.hw.hw2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Hw2Screen(viewModel: Hw2ViewModel) {
    when (val uiState = viewModel.uiState.collectAsState().value) {
        is Hw2UiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is Hw2UiState.Success -> {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(uiState.gifs.size) { index ->
                    val gif = uiState.gifs[index]
                    GifItem(gif.images.original.url)
                }
            }
        }
        is Hw2UiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Error: ${uiState.message}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadTrendingGifs() }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}
