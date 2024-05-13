package com.example.apiexample.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://192.168.0.30:5000"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface AppApiService {
    @GET("/")
    suspend fun getResponse(): String
}

object AppApi {
    val retrofitService: AppApiService by lazy {
        retrofit.create(AppApiService::class.java)
    }
}