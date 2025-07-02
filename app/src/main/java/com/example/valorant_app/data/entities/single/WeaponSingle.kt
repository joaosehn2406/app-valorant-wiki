package com.example.valorant_app.data.entities.single

import com.google.gson.annotations.SerializedName

data class WeaponSingle(
    @SerializedName("uuid")
    val uuid: String = "",

    @SerializedName("displayName")
    val displayName: String = "",

    @SerializedName("defaultSkinUuid")
    val defaultSkinUuid: String = "",

    @SerializedName("skins")
    val skins: List<Skin> = listOf()
) {
    data class Skin(
        @SerializedName("uuid")
        val uuid: String = "",

        @SerializedName("displayName")
        val displayName: String = "",

        @SerializedName("themeUuid")
        val themeUuid: String = "",

        @SerializedName("contentTierUuid")
        val contentTierUuid: String = "",

        @SerializedName("displayIcon")
        val displayIcon: String = "",

        @SerializedName("wallpaper")
        val wallpaper: String? = null,

        @SerializedName("assetPath")
        val assetPath: String = "",

        @SerializedName("chromas")
        val chromas: List<Chroma> = listOf()
    )

    data class Chroma(
        @SerializedName("uuid")
        val uuid: String = "",

        @SerializedName("displayName")
        val displayName: String = "",

        @SerializedName("displayIcon")
        val displayIcon: String = "",

        @SerializedName("fullRender")
        val fullRender: String = "",

        @SerializedName("swatch")
        val swatch: String? = null,

        @SerializedName("streamedVideo")
        val streamedVideo: String? = null,

        @SerializedName("assetPath")
        val assetPath: String = ""
    )
}