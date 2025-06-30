package com.example.valorant_app.data.services

import com.example.valorant_app.data.entities.AgentCard
import com.example.valorant_app.data.entities.WeaponCard

import retrofit2.http.GET

interface ValorantApiService {
    @GET("agents")
    suspend fun getAllAgentsCard(): ApiResponse<List<AgentCard>>

    @GET("weapon")
    suspend fun getAllWeaponCard(): ApiResponse<List<WeaponCard>>

}
