package com.example.valorant_app.data.entities

import com.google.gson.annotations.SerializedName

data class SkinCard(
    @SerializedName("displayName")
    val displayName: String = "",

    @SerializedName("displayIcon")
    val displayIcon: String = ""
)
