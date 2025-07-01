package com.example.valorant_app.data.entities.single

import com.google.gson.annotations.SerializedName

data class AgentSingle(
    @SerializedName("uuid")
    val uuid: String? = "",

    @SerializedName("fullPortrait")
    val fullPortrait: String? = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("background")
    val background: String = "",

    @SerializedName("role")
    val role: Role? = null,

    @SerializedName("abilities")
    val abilities: List<Ability> = listOf(),

    @SerializedName("isPlayableCharacter")
    val isPlayableCharacter: Boolean = true
) {
    data class Role(
        @SerializedName("displayName")
        val displayName: String = "",

        @SerializedName("description")
        val description: String = "",

        @SerializedName("displayIcon")
        val displayIcon: String = ""
    )

    data class Ability(
        @SerializedName("slot")
        val slot: String = "",

        @SerializedName("displayName")
        val displayName: String = "",

        @SerializedName("description")
        val description: String = "",

        @SerializedName("displayIcon")
        val displayIcon: String = "",
    )
}