package com.example.valorant_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.valorant_app.ui.navigation.*
import com.example.valorant_app.ui.pages.agent.single.AgentSingleScreen
import com.example.valorant_app.ui.pages.agent.card.AgentsScreen
import com.example.valorant_app.ui.pages.home.HomeContent
import com.example.valorant_app.ui.pages.initial_screen.InitialScreen
import com.example.valorant_app.ui.pages.weapon.card.WeaponSkinsScreen
import com.example.valorant_app.ui.reusable_comp.BottomAppBarNav
import com.example.valorant_app.ui.theme.ValorantappTheme
import dagger.hilt.android.AndroidEntryPoint

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconButton
import com.example.valorant_app.ui.pages.weapon.single.WeaponSingleScreen

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
                    navigateToHomePage = {
                        navController.navigate(HomePageRoute.route)
                    }
                )
            }
            composable(HomePageRoute.route) {
                AppScaffold(
                    navController,
                    currentRoute,
                    topBar = {
                        TopAppBar(
                            title = { Text("Valorant Wiki", color = Color.White) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0E0E10),
                                titleContentColor = Color(0xFFE03240)
                            )
                        )
                    }
                ) { padding ->
                    HomeContent(modifier = padding)
                }
            }
            composable(AgentRoute.route) {
                AppScaffold(
                    navController,
                    currentRoute,
                    topBar = {
                        TopAppBar(
                            title = { Text("Agentes", color = Color.White) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0E0E10),
                                titleContentColor = Color(0xFFE03240)
                            )
                        )
                    }
                ) { padding ->
                    AgentsScreen(navController = navController, modifier = padding)
                }
            }
            composable(WeaponRoute.route) {
                AppScaffold(
                    navController,
                    currentRoute,
                    topBar = {
                        TopAppBar(
                            title = { Text("Armas", color = Color.White) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF0E0E10),
                                titleContentColor = Color(0xFFE03240)
                            )
                        )
                    }
                ) { padding ->
                    WeaponSkinsScreen(navController = navController, modifier = padding)
                }
            }
            composable("AgentSingleRoute/{uuid}") { backStackEntry ->
                val agentId = backStackEntry.arguments?.getString("uuid")
                AppScaffold(
                    navController,
                    currentRoute,
                    topBar = {
                        TopAppBar(
                            title = { Text("Detalhes do Agente", color = Color.White) },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Voltar",
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
                    AgentSingleScreen(agentId = agentId ?: "", navController = navController, modifier = padding)
                }
            }
            composable("WeaponSingleRoute/{uuid}") { backStackEntry ->
                val weaponId = backStackEntry.arguments?.getString("uuid")
                AppScaffold(
                    navController,
                    currentRoute,
                    topBar = {
                        TopAppBar(
                            title = { Text("Skins da Arma", color = Color.White) },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Voltar",
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
                    WeaponSingleScreen(weaponId = weaponId ?: "", navController = navController, modifier = padding)
                }
            }
        }
    }
}