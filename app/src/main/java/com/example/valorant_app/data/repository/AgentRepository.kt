package com.example.valorant_app.data.repository

import android.util.Log
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
        val response = valorantApiService.getAllAgentsCard(language)
        return if (response.isSuccessful) {
            val body = response.body()!!
            ApiResponse(status = body.status, data = body.data)
        } else {
            val code = response.code()
            val errorText = response.errorBody()?.string().orEmpty()
            Log.e("AgentRepo", "Erro HTTP $code: $errorText")
            ApiResponse(status = code, data = emptyList())
        }
    }

    override suspend fun getAgentById(uuid: String): ApiResponse<AgentSingle> {
        val response = valorantApiService.getAgentById(uuid)
        return if (response.isSuccessful) {
            val body = response.body()!!
            ApiResponse(status = body.status , data = body.data)
        } else {
            val code = response.code()
            val errorText = response.errorBody()?.string().orEmpty()
            Log.e("AgentRepo", "Erro HTTP $code: $errorText")
            ApiResponse(status = code, data = AgentSingle())
        }
    }
}