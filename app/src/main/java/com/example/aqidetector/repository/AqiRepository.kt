package com.example.aqidetector.repository

import android.util.Log
import com.example.aqidetector.BuildConfig
import com.example.aqidetector.models.AqiReportModel
import com.example.aqidetector.network.AqiAPIService
import com.example.aqidetector.network.getRetrofitClient
import retrofit2.Response

private const val API_TOKEN = BuildConfig.AQICN_TOKEN

interface AqiRepositoryInterface {
    suspend fun getAqiHere(): Response<AqiReportModel>
}

class AqiRepository(
    private val apiService: AqiAPIService = getRetrofitClient()
): AqiRepositoryInterface {
    override suspend fun getAqiHere(): Response<AqiReportModel> {
        Log.d("Repository getAqiHere", "")
        return apiService.getAqiHere(API_TOKEN)
    }
}