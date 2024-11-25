package com.hw.hw2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Hw2ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<Hw2UiState>(Hw2UiState.Loading)
    val uiState: StateFlow<Hw2UiState> = _uiState

    private val apiKey = "sSpw6YrpjkEA2MwJDbVf6gEaCvY1MsFL"

    init {
        loadTrendingGifs()
    }

    fun loadTrendingGifs() {
        viewModelScope.launch {
            _uiState.value = Hw2UiState.Loading
            try {
                val response = Hw2Client.hw2Api.getTrendingGifs(apiKey, limit = 20, offset = 0)
                _uiState.value = Hw2UiState.Success(response.data)
            } catch (e: Exception) {
                _uiState.value = Hw2UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class Hw2UiState {
    data object Loading : Hw2UiState()
    data class Success(val gifs: List<GifObject>) : Hw2UiState()
    data class Error(val message: String) : Hw2UiState()
}
