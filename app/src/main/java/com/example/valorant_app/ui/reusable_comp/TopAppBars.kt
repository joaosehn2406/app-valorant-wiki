package com.example.valorant_app.ui.reusable_comp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.valorant_app.R
import com.example.valorant_app.data.utils.FlagHexConverter
import com.example.valorant_app.ui.pages.agent.card.compose.AgentScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    navController: NavController,
    currentRoute: String,
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = topBar,
        bottomBar = {
            BottomAppBarNav(navController = navController, currentRoute = currentRoute)
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        content(innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    navController: NavController,
    viewModel: AgentScreenViewModel
) {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        actions = {
            FlagsDropdown(
                selectedLanguage = viewModel.languageSelected,
                onLanguageSelected = viewModel::languageSelected
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentTopBar(
    onFilterClick: () -> Unit,
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.agents),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        actions = {
            Box(
                modifier = Modifier.background(Color.Transparent)
            ) {
                IconButton(
                    onClick = onFilterClick
                ) {
                    Icon(
                        painterResource(R.drawable.filter),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.weapon),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentSingleTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.agent_details),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponSingleTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                stringResource(R.string.weapon_skins),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
fun FlagsDropdown(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.background(Color.Transparent)) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            listOf(
                "pt-BR" to R.string.dropdown_portuguese,
                "en-US" to R.string.dropdown_english,
                "es-ES" to R.string.dropdown_spanish
            ).forEach { (code, labelRes) ->
                val isSelected = code == selectedLanguage
                val countryCode = code.split("-").getOrNull(1)?.uppercase() ?: code

                DropdownMenuItem(
                    text = {
                        Text(
                            "${FlagHexConverter.countryCodeToFlagEmoji(countryCode)}  ${
                                stringResource(
                                    labelRes
                                )
                            }",
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            letterSpacing = 0.5.sp
                        )
                    },
                    trailingIcon = {
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selecionado",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    onClick = {
                        onLanguageSelected(code)
                        expanded = false
                    }
                )
            }
        }
    }
}