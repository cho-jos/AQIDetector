package com.example.aqidetector

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.aqidetector.models.AqiViewModel
import com.example.aqidetector.screens.MainAqiScreen

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AqiAPIApp() {
    MainAqiScreen(viewModel = AqiViewModel())
}