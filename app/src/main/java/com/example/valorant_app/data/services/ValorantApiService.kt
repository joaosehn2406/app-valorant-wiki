package com.example.valorant_app.data.services

import com.example.valorant_app.data.entities.card.AgentCard
import com.example.valorant_app.data.entities.single.AgentSingle
import com.example.valorant_app.data.entities.card.WeaponCard
import com.example.valorant_app.data.entities.single.WeaponSingle

import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantApiService {
    @GET("agents")
    suspend fun getAllAgentsCard(): ApiResponse<List<AgentCard>>

    @GET("agents/{uuid}")
    suspend fun getAgentById(@Path("uuid") uuid: String): ApiResponse<AgentSingle>

    @GET("weapons")
    suspend fun getAllWeaponCard(): ApiResponse<List<WeaponCard>>

    @GET("weapons/{uuid}")
    suspend fun getWeaponById(@Path("uuid") uuid: String): ApiResponse<WeaponSingle>

}
