package com.example.valorant_app.data.entities

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("flags") val flags: Flags
)
data class Flags(
    @SerializedName("png") val png: String,
    @SerializedName("svg") val svg: String
)