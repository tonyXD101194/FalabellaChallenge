package com.example.falabellatest.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("/api")
    fun getMeasures(): Call<Map<String, Any>>
}