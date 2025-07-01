package com.example.valorant_app.ui.pages.agent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.valorant_app.data.utils.getCountryInfo
import com.example.valorant_app.data.utils.toComposeColor
import com.example.valorant_app.ui.theme.ValorantRed

@Composable
fun AgentsScreen(
    agentScreenViewModel: AgentScreenViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state by agentScreenViewModel.uiState.collectAsStateWithLifecycle()

    when (state) {
        is AgentUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = ValorantRed)
            }
        }
        is AgentUiState.Error -> {
            Text(
                (state as AgentUiState.Error).message,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }
        is AgentUiState.Success -> {
            val agents = (state as AgentUiState.Success).agents

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(agents) { agent ->
                    val gradientColors = agent
                        .backgroundGradientColors
                        .map { it.toComposeColor() }

                    if (agent.isPlayableCharacter) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .background(
                                    brush = Brush.horizontalGradient(colors = gradientColors),
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = agent.displayIconSmall,
                                    contentDescription = agent.displayName,
                                    modifier = Modifier.size(56.dp)
                                )
                                Spacer(Modifier.width(10.dp))

                                val country = agent.getCountryInfo()

                                Column(
                                    modifier = Modifier.fillMaxHeight()
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = agent.displayName,
                                            color = Color.White,
                                            style = MaterialTheme.typography.titleLarge.copy(
                                                textDecoration = TextDecoration.Underline
                                            )
                                        )

                                        if (country != null && country.countryIso2 != "un") {
                                            Spacer(modifier = Modifier.width(10.dp))
                                            AsyncImage(
                                                model = "https://flagcdn.com/w40/${country.countryIso2}.png",
                                                contentDescription = "Bandeira de ${country.countryName}",
                                                modifier = Modifier.size(22.dp)
                                            )
                                            Text(
                                                text = " - ${country.countryName}",
                                                color = Color.White,
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        } else {
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = " - Desconhecido",
                                                color = Color.White,
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }

                                    Spacer(Modifier.height(2.dp))

                                    if (agent.characterTags.isNullOrEmpty() ||
                                        agent.characterTags.all { it.isNullOrBlank() }) {
                                        Text(
                                            text = "Sem tags informadas.",
                                            color = Color.White,
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    } else {
                                        Column {
                                            agent.characterTags.filter { !it.isNullOrBlank() }
                                                .forEach { tag ->
                                                    Text(
                                                        text = "• $tag",
                                                        color = Color.White,
                                                        style = MaterialTheme.typography.bodySmall
                                                    )
                                                }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}
