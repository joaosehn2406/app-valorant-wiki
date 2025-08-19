package com.example.valorant_app.data.repository

import com.example.valorant_app.data.entities.card.AgentCard
import com.example.valorant_app.data.entities.single.AgentSingle
import com.example.valorant_app.data.services.ApiResponse
import com.example.valorant_app.data.services.ValorantApiService
import javax.inject.Inject

interface AgentRepository {
    suspend fun getAllAgentsCard(language: String): ApiResponse<List<AgentCard>>

    suspend fun getAgentById(uuid: String): ApiResponse<AgentSingle>
}

class AgentRepositoryImpl @Inject constructor(
    private val valorantApiService: ValorantApiService
) : AgentRepository {

    override suspend fun getAllAgentsCard(language: String): ApiResponse<List<AgentCard>> {
        return try {
            val response = valorantApiService.getAllAgentsCard(language)
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

    override suspend fun getAgentById(uuid: String): ApiResponse<AgentSingle> {
        return try {
            val response = valorantApiService.getAgentById(uuid)
            ApiResponse(
                status = response.status,
                data = response.data
            )
        } catch (e: Exception) {
            ApiResponse(
                status = 500,
                data = AgentSingle()
            )
        }
    }
}
