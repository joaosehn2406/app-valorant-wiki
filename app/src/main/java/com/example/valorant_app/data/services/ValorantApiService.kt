package com.example.valorant_app.data.services

import com.example.valorant_app.data.entities.card.AgentCard
import com.example.valorant_app.data.entities.single.AgentSingle
import com.example.valorant_app.data.entities.card.WeaponCard

import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantApiService {
    @GET("agents")
    suspend fun getAllAgentsCard(): ApiResponse<List<AgentCard>>

    @GET("agents/{uuid}")
    suspend fun getAgentSingle(@Path("uuid") uuid: String): ApiResponse<AgentSingle>

    @GET("weapons")
    suspend fun getAllWeaponCard(): ApiResponse<List<WeaponCard>>

}
