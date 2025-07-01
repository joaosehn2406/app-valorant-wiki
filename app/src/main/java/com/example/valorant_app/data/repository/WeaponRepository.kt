package com.example.valorant_app.data.repository

import com.example.valorant_app.data.entities.card.WeaponCard
import com.example.valorant_app.data.services.ApiResponse
import com.example.valorant_app.data.services.ValorantApiService
import javax.inject.Inject

interface WeaponRepository {
    suspend fun getAllWeaponCard(): ApiResponse<List<WeaponCard>>
}

class WeaponRepositoryImpl @Inject constructor(
    private val valorantApiService: ValorantApiService
) : WeaponRepository {
    override suspend fun getAllWeaponCard(): ApiResponse<List<WeaponCard>> {
        return try {
            val response = valorantApiService.getAllWeaponCard()
            ApiResponse(
                status = response.status,
                data = response.data
            )
        } catch (e: Exception) {
            ApiResponse(
                status = 500,
                data = emptyList()
            )
        }
    }
}