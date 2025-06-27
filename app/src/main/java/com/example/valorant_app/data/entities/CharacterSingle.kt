package com.example.valorant_app.data.entities

import com.google.gson.annotations.SerializedName

data class CharacterSingle(
    @SerializedName("fullPortrait")
    val fullPortrait: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("background")
    val background: String = "",

    @SerializedName("role")
    val role: Role? = null
) {
    data class Role(
        @SerializedName("displayName")
        val displayName: String = "",

        @SerializedName("description")
        val description: String = "",

        @SerializedName("displayIcon")
        val displayIcon: String = ""
    )
}