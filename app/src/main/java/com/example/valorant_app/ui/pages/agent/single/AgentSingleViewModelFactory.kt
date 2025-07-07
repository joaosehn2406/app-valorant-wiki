package com.example.valorant_app.ui.pages.agent.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class AgentSingleViewModelFactory @AssistedInject constructor(
    private val assistedFactory: AgentSingleViewModel.Factory,
    @Assisted private val agentId: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(agentId) as T
    }
}