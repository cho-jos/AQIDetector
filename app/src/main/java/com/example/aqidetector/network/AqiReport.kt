package com.example.aqidetector.network

import kotlinx.serialization.Serializable

@Serializable
data class AqiReport (
    val status: String,
    val data: Data
)

@Serializable
data class Data (
    val aqi: String,
    val city: City
)

@Serializable
data class City (
    val name: String
)