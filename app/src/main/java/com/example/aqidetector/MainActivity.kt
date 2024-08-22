package com.example.aqidetector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.aqidetector.ui.theme.AQIDetectorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AQIDetectorTheme {
                AQIDetectorApp()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AQIDetectorApp() {
    DetectButton(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Composable
fun DetectButton(modifier: Modifier) {
    // just pretend I have an AQI number
    var aqi by remember { mutableIntStateOf(0) }
    var aqiColor = when(aqi) {
        in 0..50 -> Color(0xFF00e400)
        in 51..100 -> Color(0xFFffff00)
        in 101..150 -> Color(0xFFff7e00)
        in 151..200 -> Color(0xFFff0000)
        in 201..300 -> Color(0xFF8f3f97)
        else -> Color(0xFF7e0023)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(aqiColor)
    ) {
        Button(onClick = {
            aqi = (0..500).random()
        }) {
            Text(text = "I am button")
        }
    }
}
