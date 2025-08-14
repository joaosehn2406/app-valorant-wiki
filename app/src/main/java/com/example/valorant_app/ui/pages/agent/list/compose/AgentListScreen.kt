package com.example.valorant_app.ui.pages.agent.list.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import com.example.valorant_app.data.entities.card.AgentCard
import com.example.valorant_app.data.utils.getCountryInfo
import com.example.valorant_app.data.utils.toComposeColor
import com.example.valorant_app.ui.reusable_comp.AgentTopBar
import com.example.valorant_app.ui.reusable_comp.BottomAppBarNav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentsListScreen(
    navController: NavController,
    viewModel: AgentListViewModel = hiltViewModel(),
    currentRoute: String
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var showFilter by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AgentTopBar(
                onFilterClick = { showFilter = !showFilter },
                navController = navController
            )
        },
        bottomBar = {
            BottomAppBarNav(navController = navController, currentRoute = currentRoute)
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            when (state) {
                is AgentListUiState.Loading -> {
                    Box(
                        Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primaryContainer
                        )
                    }
                }

                is AgentListUiState.Error -> {
                    Box(
                        Modifier
                            .fillMaxSize(),
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

                    AgentFilterChip(
                        showFilter = showFilter,
                        allTags = allTags,
                        selectedTags = selectedTags
                    )

                    val filteredAgents = if (selectedTags.isEmpty()) agents else
                        agents.filter {
                            it.characterTags.orEmpty()
                                .any { tag -> tag != null && selectedTags.contains(tag) }
                        }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        contentPadding = PaddingValues(
                            top = 16.dp,
                            bottom = 16.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filteredAgents.sortedBy {
                            it.displayName
                        }) { agent ->
                            AgentCardItem(agent = agent) {
                                navController.navigate("AgentSingleRoute/${agent.uuid}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AgentFilterChip(
    showFilter: Boolean,
    allTags: List<String>,
    selectedTags: SnapshotStateList<String>
) {
    AnimatedVisibility(
        visible = showFilter,
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .animateContentSize(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            allTags.forEach { tag ->
                val elevation by animateDpAsState(
                    if (selectedTags.contains(tag)) 6.dp else 3.dp
                )
                FilterChip(
                    selected = selectedTags.contains(tag),
                    onClick = {
                        if (selectedTags.contains(tag)) selectedTags.remove(tag)
                        else selectedTags.add(tag)
                    },
                    label = {
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.labelLarge,
                            color = if (selectedTags.contains(tag))
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.primary
                        )
                    },
                    shape = RoundedCornerShape(50),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = if (selectedTags.contains(tag))
                            MaterialTheme.colorScheme.primaryContainer
                        else Color.DarkGray,
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
                    elevation = FilterChipDefaults.filterChipElevation(elevation = elevation)
                )
            }
        }
    }
}

@Composable
private fun AgentCardItem(agent: AgentCard, onClick: () -> Unit) {
    val gradient = agent.backgroundGradientColors.map { it.toComposeColor() }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(
                brush = Brush.horizontalGradient(gradient),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            Modifier
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
                val tags = agent.characterTags.orEmpty().filterNot { it.isNullOrBlank() }
                Text(
                    text = if (tags.isEmpty())
                        stringResource(R.string.no_tags_informed)
                    else
                        tags.joinToString(" • "),
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}