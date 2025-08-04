package com.example.valorant_app.ui.pages.agent.list.compose

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
class AgentListViewModel @Inject constructor(
    private val agentRepository: AgentRepository
) : ViewModel() {

    var languageSelected by mutableStateOf("en-US")

    private val _uiState = MutableStateFlow<AgentListUiState>(AgentListUiState.Loading)
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
            _uiState.value = AgentListUiState.Loading
            val response = agentRepository.getAllAgentsCard(languageSelected)
            if (response.status == 200) {
                _uiState.value = AgentListUiState.Success(response.data.filter { agents ->
                    agents.isPlayableCharacter
                })
            } else {
                _uiState.value = AgentListUiState.Error("Erro: ${response.status}")
            }
        }
    }
}