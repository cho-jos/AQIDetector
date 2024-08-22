package com.example.aqidetector.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.waqi.info/"
//private val API_TOKEN = System.getenv("AQICN_TOKEN")

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

// create() on Retrofit objects is expensive. the app needs only one instance of the
// Retrofit API service, so expose the service to the rest of the app using object declaration
object AqiApi {
    val retrofitService: AqiApiService by lazy {
        retrofit.create(AqiApiService::class.java)
    }
}

interface AqiApiService {
    @GET("/feed/here/")
    suspend fun getAqiHere(@Query("token") API_TOKEN: String): AqiReport
}