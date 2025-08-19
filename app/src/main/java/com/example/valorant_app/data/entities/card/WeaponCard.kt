package com.example.valorant_app.data.entities.card

import com.google.gson.annotations.SerializedName

data class WeaponCard(
    @SerializedName("uuid")
    val uuid: String = "",

    @SerializedName("displayName")
    val displayName: String = "",

    @SerializedName("displayIcon")
    val displayIcon: String = "",

    @SerializedName("shopData")
    val shopData: ShopData? = null,
) {
    data class ShopData(
        @SerializedName("cost")
        val cost: Int = 0,

        @SerializedName("category")
        val category: String = "",
    )
}