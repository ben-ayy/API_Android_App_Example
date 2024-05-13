package com.example.apiexample.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiexample.network.AppApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainPageUiState {
    data class Success(val response: String): MainPageUiState
    data object Error: MainPageUiState
    data object Loading: MainPageUiState
}

class MainPageViews : ViewModel(){

    var response: MainPageUiState by mutableStateOf(MainPageUiState.Loading)
        private set


    fun getHelloWorld() {
        viewModelScope.launch {
            response = try {
                val result = AppApi.retrofitService.getResponse()
                MainPageUiState.Success(result)
            } catch (e: IOException) {
                MainPageUiState.Error
            }
        }
    }
}