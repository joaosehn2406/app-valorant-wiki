package com.example.valorant_app.data.entities

import com.google.gson.annotations.SerializedName

class CharacterCard {
    @SerializedName("displayName")
    val displayName: String = ""

    @SerializedName("displayIconSmall")
    val displayIconSmall: String = ""

    @SerializedName("characterTags")
    val characterTags: List<String> = listOf()
}