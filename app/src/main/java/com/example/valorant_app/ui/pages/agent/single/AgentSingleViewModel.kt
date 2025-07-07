package com.example.valorant_app.ui.pages.agent.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorant_app.data.repository.AgentRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AgentSingleViewModel @AssistedInject constructor(
    private val agentRepository: AgentRepository,
    @Assisted private val agentId: String
) : ViewModel() {

    private val _uiState = MutableStateFlow<AgentSingleUiState>(AgentSingleUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchAgentSingle()
    }

    fun fetchAgentSingle() {
        viewModelScope.launch {
            _uiState.value = AgentSingleUiState.Loading

            try {
                val response = agentRepository.getAgentById(agentId)

                if (response.status == 200) {
                    _uiState.value =
                        AgentSingleUiState.Success(response.data)
                } else {
                    _uiState.value = AgentSingleUiState.Error("Erro ao carregar agente.")
                }
            } catch (e: Exception) {
                _uiState.value =
                    AgentSingleUiState.Error("Erro de conexão: ${e.message}")
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(agentId: String) : AgentSingleViewModel
    }
}