package com.example.valorant_app.data.entities

import com.google.gson.annotations.SerializedName

data class WeaponCard(
    @SerializedName("displayName")
    val displayName: String = "",

    @SerializedName("defaultSkinUuid")
    val defaultSkinUuid: String = ""
)
