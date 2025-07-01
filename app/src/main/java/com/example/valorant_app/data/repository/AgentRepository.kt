package com.example.valorant_app.data.repository

import com.example.valorant_app.data.entities.card.AgentCard
import com.example.valorant_app.data.entities.single.AgentSingle
import com.example.valorant_app.data.services.ApiResponse
import com.example.valorant_app.data.services.ValorantApiService
import javax.inject.Inject

interface AgentRepository {
    suspend fun getAllAgentsCard(): ApiResponse<List<AgentCard>>

    suspend fun getAgentSingle(uuid: String): ApiResponse<AgentSingle>
}

class AgentRepositoryImpl @Inject constructor(
    private val valorantApiService: ValorantApiService
) : AgentRepository {

    override suspend fun getAllAgentsCard(): ApiResponse<List<AgentCard>> {
        return try {
            val response = valorantApiService.getAllAgentsCard()
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

    override suspend fun getAgentSingle(uuid: String): ApiResponse<AgentSingle> {
        return try {
            val response = valorantApiService.getAgentSingle(uuid)
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
