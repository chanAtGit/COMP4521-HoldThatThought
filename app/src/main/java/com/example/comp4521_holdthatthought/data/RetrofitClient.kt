package com.example.comp4521_holdthatthought.data

import com.squareup.okhttp3.OkHttpClient
import com.squareup.okhttp3.logging.HttpLoggingInterceptor
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType

object RetrofitClient {
    private const val BASE_URL = "https://comp4521-ai.boriskriuk-powered.com"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val aiLearningService: AILearningApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(AILearningApiService::class.java)
}
