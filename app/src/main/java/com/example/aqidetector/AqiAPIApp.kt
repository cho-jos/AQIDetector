package com.example.aqidetector

import androidx.compose.runtime.Composable
import com.example.aqidetector.models.AqiViewModel
import com.example.aqidetector.screens.MainAqiScreen

@Composable
fun AqiAPIApp() {
    MainAqiScreen(viewModel = AqiViewModel())
}