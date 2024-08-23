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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aqidetector.R
import com.example.aqidetector.models.AqiDetails
import com.example.aqidetector.models.AqiViewModel

@Composable
fun MainAqiScreen(
    viewModel: AqiViewModel
) {
    val aqiReport = viewModel.aqiReport.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    var aqi by remember { mutableIntStateOf(-1) }
    val aqiDetails = AqiDetails(Color.White, "", "", "")
    when (aqi) {
        in -1..0 -> { /*keep initial color and blank info*/ }
        in 0..50 -> {
            aqiDetails.aqiColor = colorResource(R.color.aqi_color_green)
            aqiDetails.airPollutionLevel = stringResource(R.string.aqi_pollution_good)
            aqiDetails.healthImplications = stringResource(R.string.aqi_health_implications_good)
            aqiDetails.cautionaryStatement = stringResource(R.string.aqi_caution_good)
        }
        in 51..100 -> {
            aqiDetails.aqiColor = colorResource(R.color.aqi_color_yellow)
            aqiDetails.airPollutionLevel = stringResource(R.string.aqi_pollution_moderate)
            aqiDetails.healthImplications = stringResource(R.string.aqi_health_implications_moderate)
            aqiDetails.cautionaryStatement = stringResource(R.string.aqi_caution_moderate)
        }
        in 101..150 -> {
            aqiDetails.aqiColor = colorResource(R.color.aqi_color_orange)
            aqiDetails.airPollutionLevel = stringResource(R.string.aqi_pollution_unhealthy_sensitive)
            aqiDetails.healthImplications = stringResource(R.string.aqi_health_implications_unhealthy_sensitive)
            aqiDetails.cautionaryStatement = stringResource(R.string.aqi_caution_unhealthy_sensitive)
        }
        in 151..200 -> {
            aqiDetails.aqiColor = colorResource(R.color.aqi_color_red)
            aqiDetails.airPollutionLevel = stringResource(R.string.aqi_pollution_unhealthy)
            aqiDetails.healthImplications = stringResource(R.string.aqi_health_implications_unhealthy)
            aqiDetails.cautionaryStatement = stringResource(R.string.aqi_caution_unhealthy)
        }
        in 201..300 -> {
            aqiDetails.aqiColor = colorResource(R.color.aqi_color_purple)
            aqiDetails.airPollutionLevel = stringResource(R.string.aqi_pollution_very_unhealthy)
            aqiDetails.healthImplications = stringResource(R.string.aqi_health_implications_very_unhealthy)
            aqiDetails.cautionaryStatement = stringResource(R.string.aqi_caution_very_unhealthy)
        }
        else -> {
            aqiDetails.aqiColor = colorResource(R.color.aqi_color_maroon)
            aqiDetails.airPollutionLevel = stringResource(R.string.aqi_pollution_hazardous)
            aqiDetails.healthImplications = stringResource(R.string.aqi_health_implications_hazardous)
            aqiDetails.cautionaryStatement = stringResource(R.string.aqi_caution_hazardous)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(aqiDetails.aqiColor)
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
                ReportRow(aqi, city, aqiDetails)
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
fun ReportRow(aqi: Int, city: String, aqiDetails: AqiDetails) {
    // trying to keep text readable
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
        Text("Air Pollution Level: ${aqiDetails.airPollutionLevel}", color = textColor)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Health Implications: ${aqiDetails.healthImplications}", color = textColor)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Caution: ${aqiDetails.cautionaryStatement}", color = textColor)
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