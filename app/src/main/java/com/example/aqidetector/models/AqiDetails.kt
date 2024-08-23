package com.example.aqidetector.models

import androidx.compose.ui.graphics.Color

data class AqiDetails(
    var aqiColor: Color,
    var airPollutionLevel: String,
    var healthImplications: String,
    var cautionaryStatement: String
)
