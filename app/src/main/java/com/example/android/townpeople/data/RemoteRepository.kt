package com.example.android.townpeople.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RemoteRepository {

    @GET("data.json")
    suspend fun getPeople(): ApiResponse

    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/rrafols/mobile_test/master/"

        fun create() : RemoteRepository {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RemoteRepository::class.java)
        }
    }
}