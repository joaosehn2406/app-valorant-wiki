package com.example.valorant_app.ui.pages.agent.single

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.valorant_app.ui.theme.ValorantRed

@Composable
fun AgentSingleScreen(
    agentId: String,
    navController: NavController,
    agentSingleViewModel: AgentSingleViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    agentSingleViewModel.fetchAgentSingle(agentId)

    val state = agentSingleViewModel.uiState.collectAsStateWithLifecycle().value

    when (state) {
        is AgentSingleUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = ValorantRed)
            }
        }

        is AgentSingleUiState.Error -> {
            Text(
                text = (state as AgentSingleUiState.Error).message,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }

        is AgentSingleUiState.Success -> {
            val agent = state.agent


        }
    }
}
