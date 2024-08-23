package com.example.aqidetector.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AqiReportModel(
    val status: String,
    val data: AqiReportData
): Parcelable

@Parcelize
data class AqiReportData(
    @SerializedName("aqi") val aqi: Int,
    @SerializedName("city") val city: AqiReportDataCity
): Parcelable

@Parcelize
data class AqiReportDataCity(
    @SerializedName("name") val name: String,
    @SerializedName("geo") val geoCoordinates: List<Double>
): Parcelable