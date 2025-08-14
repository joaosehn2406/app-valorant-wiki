package com.example.valorant_app.ui.pages.agent.list.compose

import com.example.valorant_app.data.entities.card.AgentCard

sealed class AgentListUiState {
    object Loading : AgentListUiState()
    data class Success(val agents: List<AgentCard>) : AgentListUiState()
    data class Error(val message: String) : AgentListUiState()
}