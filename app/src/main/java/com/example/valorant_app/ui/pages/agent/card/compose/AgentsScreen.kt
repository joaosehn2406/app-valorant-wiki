package com.example.valorant_app.ui.pages.agent.card.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.valorant_app.R
import com.example.valorant_app.data.utils.getCountryInfo
import com.example.valorant_app.data.utils.toComposeColor
import com.example.valorant_app.ui.theme.ValorantRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentsScreen(
    navController: NavController,
    viewModel: AgentScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier.fillMaxSize()) {
        when (state) {
            is AgentCardUiState.Loading -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = ValorantRed)
                }
            }
            is AgentCardUiState.Error -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        (state as AgentCardUiState.Error).message,
                        color = Color.Red
                    )
                }
            }
            is AgentCardUiState.Success -> {
                val agents = (state as AgentCardUiState.Success).agents

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = 8.dp,
                        bottom = 80.dp,
                        start = 6.dp,
                        end = 6.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(agents) { agent ->
                        if (!agent.isPlayableCharacter) return@items

                        val gradient = agent.backgroundGradientColors.map { it.toComposeColor() }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(bottom = 8.dp)
                                .background(
                                    brush = Brush.horizontalGradient(gradient),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    navController.navigate("AgentSingleRoute/${agent.uuid}")
                                },
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Row(
                                Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = agent.displayIconSmall,
                                    contentDescription = agent.displayName,
                                    modifier = Modifier.size(56.dp)
                                )
                                Spacer(Modifier.width(10.dp))
                                Column(Modifier.fillMaxHeight()) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = agent.displayName,
                                            color = Color.White,
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                        agent.getCountryInfo()
                                            ?.takeIf { it.countryIso2 != "un" }
                                            ?.let { country ->
                                                Spacer(Modifier.width(8.dp))
                                                AsyncImage(
                                                    model = "https://flagcdn.com/w40/${country.countryIso2}.png",
                                                    contentDescription = country.countryName,
                                                    modifier = Modifier.size(22.dp)
                                                )
                                                Text(
                                                    text = " ${country.countryName}",
                                                    color = Color.White,
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                            }
                                    }
                                    Spacer(Modifier.height(4.dp))
                                    val tags = agent.characterTags
                                        .filterNot { it.isNullOrBlank() }
                                    Text(
                                        text = if (tags.isEmpty()) {
                                            stringResource(R.string.no_tags_informed)
                                        } else {
                                            tags.joinToString(" • ")
                                        },
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

        ExtendedFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            text = { Text("XML") },
            onClick = { navController.navigate("agentsXml") }
        )
    }
}
