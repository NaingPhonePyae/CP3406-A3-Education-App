package com.example.a3_education_app.data

import com.example.a3_education_app.network.ExoplanetApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val exoplanetRepository: ExoplanetRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://exoplanetarchive.ipac.caltech.edu/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: ExoplanetApiService by lazy {
        retrofit.create(ExoplanetApiService::class.java)
    }

    override val exoplanetRepository: ExoplanetRepository by lazy {
        NetworkExoplanetRepository(retrofitService)
    }
}