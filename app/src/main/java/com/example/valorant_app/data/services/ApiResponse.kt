package com.example.valorant_app.data.services

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("status")
    val status: Int = 0,

    @SerializedName("data")
    val data: T
)