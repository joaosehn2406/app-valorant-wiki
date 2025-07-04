package com.example.valorant_app.ui.pages.agent.card

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var languageSelected by mutableStateOf("en-US")

    private val _uiState = MutableStateFlow<AgentCardUiState>(AgentCardUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun languageSelected(language: String) {
        languageSelected = language
        fetchAllAgents()
    }

    init {
        fetchAllAgents()
    }

    private fun fetchAllAgents() {
        viewModelScope.launch {
            _uiState.value = AgentCardUiState.Loading
            val response = agentRepository.getAllAgentsCard(languageSelected)
            if (response.status == 200) {
                _uiState.value = AgentCardUiState.Success(response.data)
            } else {
                _uiState.value = AgentCardUiState.Error("Erro: ${response.status}")
            }
        }
    }
}