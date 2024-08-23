package com.example.aqidetector.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aqidetector.R
import com.example.aqidetector.models.AqiViewModel

@Composable
fun MainAqiScreen(
    viewModel: AqiViewModel
) {
    val aqiReport = viewModel.aqiReport.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    var aqi by remember { mutableIntStateOf(-1) }
    var aqiColor = when(aqi) {
        in -1..0 -> Color.White
        in 0..50 -> colorResource(R.color.aqi_color_green)
        in 51..100 -> colorResource(R.color.aqi_color_yellow)
        in 101..150 -> colorResource(R.color.aqi_color_orange)
        in 151..200 -> colorResource(R.color.aqi_color_red)
        in 201..300 -> colorResource(R.color.aqi_color_purple)
        else -> colorResource(R.color.aqi_color_maroon)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(aqiColor)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (errorMessage.value != null) {
                ErrorState()
            } else if (isLoading.value) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(200.dp),
                        color = Color.Yellow,
                        trackColor = Color.Red
                    )
                }
            } else {
                aqi = aqiReport.value?.data?.aqi ?: 0
                val city = aqiReport.value?.data?.city?.name ?: ""
                ReportRow(aqi, city)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = { viewModel.getAqiReportHere() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text(text = "Detect AQI", color = Color.White)
            }
        }
    }
}

@Composable
fun ReportRow(aqi: Int, city: String) {
    val textColor = when(aqi) {
        in -1..100 -> Color.Black
        else -> Color.White
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(text = "AQI: $aqi", color = textColor)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "City: $city", color = textColor)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row {
            Text("Description")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Health Implications")
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Caution")
        }
    }
}

@Composable
fun ErrorState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null
        )
        Text(
            text = "Error!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}