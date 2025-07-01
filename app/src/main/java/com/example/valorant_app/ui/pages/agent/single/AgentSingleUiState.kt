package com.example.valorant_app.ui.pages.agent.single

import com.example.valorant_app.data.entities.AgentSingle

sealed class AgentSingleUiState {
    object Loading : AgentSingleUiState()
    data class Success(val agent: AgentSingle) : AgentSingleUiState()
    data class Error(val message: String) : AgentSingleUiState()
}
