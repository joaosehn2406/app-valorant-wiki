package com.example.valorant_app.data.services

import com.example.valorant_app.data.entities.AgentCard
import com.example.valorant_app.data.entities.WeaponCard

import retrofit2.http.GET

interface ApiService {
    @GET("agents")
    suspend fun getAllAgentsCard() : ApiResponse<List<AgentCard>>

    @GET("weapon")
    suspend fun getWeaponCard() : ApiResponse<List<WeaponCard>>
}