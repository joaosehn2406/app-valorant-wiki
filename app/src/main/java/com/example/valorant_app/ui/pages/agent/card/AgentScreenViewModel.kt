package com.example.valorant_app.ui.pages.agent.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorant_app.data.repository.AgentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentScreenViewModel @Inject constructor(
    private val agentRepository: AgentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AgentCardUiState>(AgentCardUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val response = agentRepository.getAllAgentsCard()
            if (response.status == 200) {
                _uiState.value = AgentCardUiState.Success(response.data)
            } else {
                _uiState.value = AgentCardUiState.Error("Erro: ${response.status}")
            }
        }
    }
}