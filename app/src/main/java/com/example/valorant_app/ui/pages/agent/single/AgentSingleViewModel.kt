package com.example.valorant_app.ui.pages.agent.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorant_app.data.repository.AgentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentSingleViewModel @Inject constructor(
    private val agentRepository: AgentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AgentSingleUiState>(AgentSingleUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchAgentSingle(agentId: String) {
        viewModelScope.launch {
            _uiState.value = AgentSingleUiState.Loading

            try {
                val response = agentRepository.getAgentSingle(agentId)

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
}