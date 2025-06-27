package com.example.valorant_app.data.repository

import com.example.valorant_app.data.entities.WeaponCard
import com.example.valorant_app.data.services.ApiResponse
import com.example.valorant_app.data.services.ApiService
import javax.inject.Inject

interface WeaponRepository {
    suspend fun getAllWeaponCard(): ApiResponse<List<WeaponCard>>
}

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WeaponRepository {
    override suspend fun getAllWeaponCard(): ApiResponse<List<WeaponCard>> {
        return try {
            val response = apiService.getAllWeaponCard()
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