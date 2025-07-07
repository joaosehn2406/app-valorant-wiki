package com.example.valorant_app.data.entities.single

import com.google.gson.annotations.SerializedName

data class WeaponSingle(
    @SerializedName("uuid")
    val uuid: String = "",

    @SerializedName("displayName")
    val displayName: String = "",

    @SerializedName("category")
    val category: String = "",

    @SerializedName("defaultSkinUuid")
    val defaultSkinUuid: String = "",

    @SerializedName("displayIcon")
    val displayIcon: String = "",

    @SerializedName("killStreamIcon")
    val killStreamIcon: String = "",

    @SerializedName("assetPath")
    val assetPath: String = "",

    @SerializedName("weaponStats")
    val weaponStats: WeaponStats? = null,

    @SerializedName("shopData")
    val shopData: ShopData? = null,

    @SerializedName("skins")
    val skins: List<Skin> = listOf()
) {
    data class WeaponStats(
        @SerializedName("fireRate")
        val fireRate: Float = 0f,

        @SerializedName("magazineSize")
        val magazineSize: Int = 0,

        @SerializedName("runSpeedMultiplier")
        val runSpeedMultiplier: Float = 0f,

        @SerializedName("equipTimeSeconds")
        val equipTimeSeconds: Float = 0f,

        @SerializedName("reloadTimeSeconds")
        val reloadTimeSeconds: Float = 0f,

        @SerializedName("firstBulletAccuracy")
        val firstBulletAccuracy: Float = 0f,

        @SerializedName("shotgunPelletCount")
        val shotgunPelletCount: Int = 0,

        @SerializedName("wallPenetration")
        val wallPenetration: String = "",

        @SerializedName("feature")
        val feature: String? = null,

        @SerializedName("fireMode")
        val fireMode: String? = null,

        @SerializedName("altFireType")
        val altFireType: String? = null,

        @SerializedName("adsStats")
        val adsStats: AdsStats? = null,

        @SerializedName("altShotgunStats")
        val altShotgunStats: AltShotgunStats? = null,

        @SerializedName("airBurstStats")
        val airBurstStats: AirBurstStats? = null,

        @SerializedName("damageRanges")
        val damageRanges: List<DamageRange> = listOf()
    )

    data class AdsStats(
        @SerializedName("zoomMultiplier")
        val zoomMultiplier: Float = 0f,

        @SerializedName("fireRate")
        val fireRate: Float = 0f,

        @SerializedName("runSpeedMultiplier")
        val runSpeedMultiplier: Float = 0f,

        @SerializedName("burstCount")
        val burstCount: Int = 0,

        @SerializedName("firstBulletAccuracy")
        val firstBulletAccuracy: Float = 0f
    )

    data class AltShotgunStats(
        @SerializedName("shotgunPelletCount")
        val shotgunPelletCount: Int = 0,

        @SerializedName("burstRate")
        val burstRate: Float = 0f
    )

    data class AirBurstStats(
        @SerializedName("shotgunPelletCount")
        val shotgunPelletCount: Int = 0,

        @SerializedName("burstDistance")
        val burstDistance: Float = 0f
    )

    data class DamageRange(
        @SerializedName("rangeStartMeters")
        val rangeStartMeters: Float = 0f,

        @SerializedName("rangeEndMeters")
        val rangeEndMeters: Float = 0f,

        @SerializedName("headDamage")
        val headDamage: Float = 0f,

        @SerializedName("bodyDamage")
        val bodyDamage: Float = 0f,

        @SerializedName("legDamage")
        val legDamage: Float = 0f
    )

    data class ShopData(
        @SerializedName("cost")
        val cost: Int = 0,

        @SerializedName("category")
        val category: String = "",

        @SerializedName("shopOrderPriority")
        val shopOrderPriority: Int = 0,

        @SerializedName("categoryText")
        val categoryText: String = "",

        @SerializedName("gridPosition")
        val gridPosition: GridPosition? = null,

        @SerializedName("canBeTrashed")
        val canBeTrashed: Boolean = false,

        @SerializedName("image")
        val image: String? = null,

        @SerializedName("newImage")
        val newImage: String? = null,

        @SerializedName("newImage2")
        val newImage2: String? = null,

        @SerializedName("assetPath")
        val assetPath: String = ""
    )

    data class GridPosition(
        @SerializedName("row")
        val row: Int = 0,

        @SerializedName("column")
        val column: Int = 0
    )

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
        val displayIcon: String? = null,

        @SerializedName("wallpaper")
        val wallpaper: String? = null,

        @SerializedName("assetPath")
        val assetPath: String = "",

        @SerializedName("chromas")
        val chromas: List<Chroma> = listOf(),

        @SerializedName("levels")
        val levels: List<Level> = listOf()
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

    data class Level(
        @SerializedName("uuid")
        val uuid: String = "",

        @SerializedName("displayName")
        val displayName: String = "",

        @SerializedName("levelItem")
        val levelItem: String? = null,

        @SerializedName("displayIcon")
        val displayIcon: String? = null,

        @SerializedName("streamedVideo")
        val streamedVideo: String? = null,

        @SerializedName("assetPath")
        val assetPath: String = ""
    )
}