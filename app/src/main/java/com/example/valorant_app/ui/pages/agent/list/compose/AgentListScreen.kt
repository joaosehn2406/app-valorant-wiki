package com.example.valorant_app.ui.pages.agent.list.compose

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentsListScreen(
    navController: NavController,
    viewModel: AgentListViewModel = hiltViewModel(),
    showFilter: Boolean,
    modifier: Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)
    ) {
        when (state) {
            is AgentListUiState.Loading -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primaryContainer)
                }
            }

            is AgentListUiState.Error -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        (state as AgentListUiState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            is AgentListUiState.Success -> {
                val agents = (state as AgentListUiState.Success).agents
                val selectedTags = remember { mutableStateListOf<String>() }

                val allTags = agents
                    .flatMap {
                        it.characterTags.orEmpty().filterNotNull().filterNot { it.isBlank() }
                    }
                    .distinct()

                if (showFilter) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 8.dp)
                            .animateContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        allTags.forEach { tag ->
                            FilterChip(
                                selected = selectedTags.contains(tag),
                                onClick = {
                                    if (selectedTags.contains(tag)) {
                                        selectedTags.remove(tag)
                                    } else {
                                        selectedTags.add(tag)
                                    }
                                },
                                label = {
                                    Text(
                                        text = tag,
                                        style = MaterialTheme.typography.labelLarge,
                                    )
                                },
                                shape = RoundedCornerShape(50),
                                border = FilterChipDefaults.filterChipBorder(
                                    borderColor = if (selectedTags.contains(tag)) MaterialTheme.colorScheme.primaryContainer else Color.DarkGray,
                                    borderWidth = 1.dp,
                                    enabled = true,
                                    selected = selectedTags.contains(tag)
                                ),
                                colors = FilterChipDefaults.filterChipColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                elevation = FilterChipDefaults.filterChipElevation(
                                    elevation = if (selectedTags.contains(tag)) 6.dp else 2.dp
                                ),
                                modifier = Modifier
                                    .height(36.dp)
                                    .padding(horizontal = 4.dp)
                            )
                        }
                    }
                }

                val filteredAgents = if (selectedTags.isEmpty()) {
                    agents
                } else {
                    agents.filter { agent ->
                        agent.characterTags.orEmpty().any { tag ->
                            tag != null && selectedTags.contains(tag)
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = 0.dp,
                        bottom = 6.dp,
                        start = 6.dp,
                        end = 6.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredAgents) { agent ->
                        val gradient =
                            agent.backgroundGradientColors.map { it.toComposeColor() }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .height(90.dp)
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
                                    val tags = agent.characterTags.orEmpty()
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
    }
}