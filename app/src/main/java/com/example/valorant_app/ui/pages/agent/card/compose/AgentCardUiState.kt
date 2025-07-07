package com.example.valorant_app.ui.pages.agent.card.compose

import com.example.valorant_app.data.entities.card.AgentCard

sealed class AgentCardUiState {
    object Loading : AgentCardUiState()
    data class Success(val agents: List<AgentCard>) : AgentCardUiState()
    data class Error(val message: String) : AgentCardUiState()
}