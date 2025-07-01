package com.example.valorant_app.data.entities.card

import com.google.gson.annotations.SerializedName

data class AgentCard (
    @SerializedName("uuid")
    val uuid: String = "",

    @SerializedName("displayName")
    val displayName: String = "",

    @SerializedName("displayIconSmall")
    val displayIconSmall: String = "",

    @SerializedName("characterTags")
    val characterTags: List<String?> = listOf(),

    @SerializedName("backgroundGradientColors")
    val backgroundGradientColors: List<String> = listOf(),

    @SerializedName("isPlayableCharacter")
    val isPlayableCharacter: Boolean = true
)