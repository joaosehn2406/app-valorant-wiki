package com.example.valorant_app.ui

import com.example.valorant_app.data.entities.AgentCard

sealed class AgentUiState {
    object Loading : AgentUiState()
    data class Success(val agents: List<AgentCard>) : AgentUiState()
    data class Error(val message: String) : AgentUiState()
}
