package com.example.gatewayassignment.viewmodel

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("https://jsonplaceholder.typicode.com/todos/1")
    suspend fun getTodo(): Response<JsonObject>
}
