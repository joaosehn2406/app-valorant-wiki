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
import com.example.valorant_app.ui.destinations.*
import com.example.valorant_app.ui.pages.*
import com.example.valorant_app.ui.pages.agent.AgentsScreen
import com.example.valorant_app.ui.pages.home.HomeContent
import com.example.valorant_app.ui.pages.skin.SkinsScreen
import com.example.valorant_app.ui.reusable_comp.BottomAppBarNav
import com.example.valorant_app.ui.theme.ValorantappTheme

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
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Valorant Wiki", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0E0E10),
                    titleContentColor = Color(0xFFE03240)
                )
            )
        },
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
                AppScaffold(navController, currentRoute) { padding ->
                    HomeContent(modifier = padding)
                }
            }
            composable(AgentRoute.route) {
                AppScaffold(navController, currentRoute) { padding ->
                    AgentsScreen(navController = navController, modifier = padding)
                }
            }
            composable(WeaponRoute.route) {
                AppScaffold(navController, currentRoute) { padding ->
                    SkinsScreen(navController = navController, modifier = padding)
                }
            }
        }
    }
}
