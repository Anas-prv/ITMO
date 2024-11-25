package com.hw.hw2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Hw2ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<Hw2UiState>(Hw2UiState.Loading)
    val uiState: StateFlow<Hw2UiState> = _uiState

    private val apiKey = "API_key"

    private var offset = 0
    private var isLoadingMore = false
    private var canLoadMore = true

    init {
        loadTrendingGifs()
    }

    fun loadTrendingGifs() {
        viewModelScope.launch {
            _uiState.value = Hw2UiState.Loading
            offset = 0
            try {
                val response = Hw2Client.hw2Api.getTrendingGifs(apiKey, limit = 20, offset = offset)
                offset += response.data.size
                canLoadMore = response.data.isNotEmpty()
                _uiState.value = Hw2UiState.Success(response.data, isLoadingMore = false)
            } catch (e: Exception) {
                _uiState.value = Hw2UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun loadMoreGifs() {
        if (isLoadingMore || !canLoadMore) return
        isLoadingMore = true

        viewModelScope.launch {
            try {
                val response = Hw2Client.hw2Api.getTrendingGifs(apiKey, limit = 20, offset = offset)
                offset += response.data.size
                canLoadMore = response.data.isNotEmpty()
                val currentState = _uiState.value
                if (currentState is Hw2UiState.Success) {
                    _uiState.value = Hw2UiState.Success(
                        gifs = currentState.gifs + response.data,
                        isLoadingMore = false
                    )
                }
            } catch (e: Exception) {
                val currentState = _uiState.value
                if (currentState is Hw2UiState.Success) {
                    _uiState.value = Hw2UiState.Success(
                        gifs = currentState.gifs,
                        isLoadingMore = false
                    )
                }
            } finally {
                isLoadingMore = false
            }
        }
    }


}

sealed class Hw2UiState {
    data object Loading : Hw2UiState()
    data class Success(
        val gifs: List<GifObject>,
        val isLoadingMore: Boolean = false
    ) : Hw2UiState()
    data class Error(val message: String) : Hw2UiState()
}
