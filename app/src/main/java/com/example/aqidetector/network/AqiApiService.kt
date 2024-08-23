package com.example.aqidetector.network

import com.example.aqidetector.models.AqiReportModel
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.waqi.info/"

interface AqiAPIService {
    @GET("feed/here")
    suspend fun getAqiHere(
        @Query("token") token: String
    ): Response<AqiReportModel>
}

// in order to have a complete Retrofit client,
// we need to instantiate Retrofit and associate with out API service
fun getRetrofitClient(): AqiAPIService {
    val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
    return client.create(AqiAPIService::class.java)
}