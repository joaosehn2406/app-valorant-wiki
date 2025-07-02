package com.example.valorant_app.ui.pages.agent.single

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
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
            val scrollState = rememberScrollState()

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .background(Color.Gray)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(agent.background),
                            contentDescription = "Foto do background",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 40.dp, top = 115.dp)
                                .align(Alignment.Center),
                            contentScale = ContentScale.Crop
                        )

                        Image(
                            painter = rememberAsyncImagePainter(agent.fullPortrait),
                            contentDescription = "Foto do agente",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(430.dp)
                                .padding(top = 110.dp, start = 15.dp)
                                .align(Alignment.Center)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black.copy(alpha = 0.8f))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = agent.displayName,
                            style = MaterialTheme.typography.headlineMedium,
                            color = ValorantRed,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = agent.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Text(
                            text = "Role: ${agent.role?.displayName ?: "Unknown"}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = ValorantRed,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        agent.abilities.forEach { ability ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(ability.displayIcon),
                                    contentDescription = ability.displayName,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.DarkGray)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = ability.displayName,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color.White
                                    )
                                    Text(
                                        text = ability.description,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.LightGray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}