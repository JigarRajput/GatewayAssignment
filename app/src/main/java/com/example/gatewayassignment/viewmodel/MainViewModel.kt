package com.example.gatewayassignment.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gatewayassignment.network.RetrofitInstance
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var apiResult by mutableStateOf("")
    var isDialogVisible by mutableStateOf(true)
        private set

    fun callApi() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTodo()
                if (response.isSuccessful) {
                    apiResult = GsonBuilder().setPrettyPrinting().create()
                        .toJson(JsonParser.parseString(response.body().toString()))
                }
                isDialogVisible = true
            } catch (e: Exception) {
                apiResult = "API Error: ${e.message}"
            }
        }
    }

    fun dismissDialog() {
        isDialogVisible = false
    }
}
