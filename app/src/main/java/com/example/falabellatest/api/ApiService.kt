package com.example.falabellatest.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    companion object {

        private const val ENDPOINT = "https://www.mindicador.cl"

        private lateinit var retrofit: Retrofit

        fun getApiService(): Retrofit {

            if (!this::retrofit.isInitialized) {

                retrofit = Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }
    }
}