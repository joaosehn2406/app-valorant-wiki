package com.example.valorant_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.valorant_app.data.utils.FlagHexConverter
import com.example.valorant_app.ui.navigation.AgentRoute
import com.example.valorant_app.ui.navigation.HomePageRoute
import com.example.valorant_app.ui.navigation.InitialPageRoute
import com.example.valorant_app.ui.navigation.WeaponRoute
import com.example.valorant_app.ui.pages.agent.card.AgentsScreen
import com.example.valorant_app.ui.pages.agent.single.AgentSingleScreen
import com.example.valorant_app.ui.pages.home.HomeContent
import com.example.valorant_app.ui.pages.initial_screen.InitialScreen
import com.example.valorant_app.ui.pages.weapon.card.WeaponSkinsScreen
import com.example.valorant_app.ui.pages.weapon.single.WeaponSingleScreen
import com.example.valorant_app.ui.reusable_comp.BottomAppBarNav
import com.example.valorant_app.ui.theme.ValorantappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ValorantWikiApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    navController: NavController,
    currentRoute: String,
    topBar: @Composable () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = topBar,
        bottomBar = {
            BottomAppBarNav(navController = navController, currentRoute = currentRoute)
        },
        containerColor = Color.Transparent
    ) { inner ->
        content(Modifier.padding(inner))
    }
}

@Composable
fun FlagsDropdown(
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.background(
            Color.Transparent
        )
    ) {
        IconButton(
            onClick = {expanded = !expanded}
        ) {
            Icon(
                Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            DropdownMenuItem(
                text = { Text("${FlagHexConverter.countryCodeToFlagEmoji("BR")} - Português") },
                onClick = { }
            )
            DropdownMenuItem(
                text = { Text("${FlagHexConverter.countryCodeToFlagEmoji("US")} - Inglês") },
                onClick = { }
            )
            DropdownMenuItem(
                text = { Text("${FlagHexConverter.countryCodeToFlagEmoji("ES")} - Espanhol") },
                onClick = { }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValorantWikiApp() {
    ValorantappTheme {
        val navController = rememberNavController()
        val backStack by navController.currentBackStackEntryAsState()
        val currentRoute = backStack?.destination?.route ?: ""

        NavHost(
            navController = navController,
            startDestination = InitialPageRoute.route
        ) {
            composable(InitialPageRoute.route) {
                InitialScreen(
                    navigateToHomePage = { navController.navigate(HomePageRoute.route) }
                )
            }
            composable(HomePageRoute.route) {
                AppScaffold(
                    navController,
                    currentRoute,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    stringResource(R.string.app_name),
                                    color = Color.White
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0E0E10),
                                titleContentColor = Color(0xFFE03240)
                            ),
                            actions = {
                                FlagsDropdown()
                            }
                        )
                    }
                ) { padding -> HomeContent(modifier = padding) }
            }
            composable(AgentRoute.route) {
                AppScaffold(
                    navController,
                    currentRoute,
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(R.string.agents), color = Color.White) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0E0E10),
                                titleContentColor = Color(0xFFE03240)
                            )
                        )
                    }
                ) { padding -> AgentsScreen(navController = navController, modifier = padding) }
            }
            composable(WeaponRoute.route) {
                AppScaffold(
                    navController,
                    currentRoute,
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(R.string.weapon), color = Color.White) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0E0E10),
                                titleContentColor = Color(0xFFE03240)
                            )
                        )
                    }
                ) { padding ->
                    WeaponSkinsScreen(
                        navController = navController,
                        modifier = padding
                    )
                }
            }
            composable("AgentSingleRoute/{uuid}") { backStackEntry ->
                val agentId = backStackEntry.arguments?.getString("uuid") ?: ""
                AppScaffold(
                    navController,
                    currentRoute,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    stringResource(R.string.agent_details),
                                    color = Color.White
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        null,
                                        tint = Color.White
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0E0E10),
                                titleContentColor = Color(0xFFE03240),
                                navigationIconContentColor = Color.White
                            )
                        )
                    }
                ) { padding ->
                    AgentSingleScreen(
                        agentId = agentId,
                        navController = navController,
                        modifier = padding
                    )
                }
            }
            composable("WeaponSingleRoute/{uuid}") { backStackEntry ->
                val weaponId = backStackEntry.arguments?.getString("uuid") ?: ""
                AppScaffold(
                    navController,
                    currentRoute,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    stringResource(R.string.weapon_skins),
                                    color = Color.White
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        null,
                                        tint = Color.White
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0E0E10),
                                titleContentColor = Color(0xFFE03240),
                                navigationIconContentColor = Color.White
                            )
                        )
                    }
                ) { padding ->
                    WeaponSingleScreen(
                        weaponId = weaponId,
                        navController = navController,
                        modifier = padding
                    )
                }
            }
        }
    }
}
