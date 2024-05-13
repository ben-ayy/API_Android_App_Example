package com.example.apiexample.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLayout(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "API Example App") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) {
        val mainPageViewModel: MainPageViews = viewModel()
        AppContent(mainPageViewModel, innerPadding = it)
    }
}

@Composable
fun AppContent(viewModel: MainPageViews, modifier: Modifier = Modifier, innerPadding: PaddingValues) {
    var result by remember { mutableStateOf("") }
    var response = viewModel.response
    result = when (response) {
        is MainPageUiState.Success -> response.response
        is MainPageUiState.Loading -> ""
        is MainPageUiState.Error -> "Connection Failed..."
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                response = MainPageUiState.Loading
                viewModel.getHelloWorld()
                response = viewModel.response
            },
            content = { Text(text="API GET") }
        )
        Text(
            text = result
        )
    }
}