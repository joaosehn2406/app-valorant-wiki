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
import androidx.navigation.compose.*
import com.example.valorant_app.ui.destinations.*
import com.example.valorant_app.ui.pages.*
import com.example.valorant_app.ui.pages.agent.AgentsScreen
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
fun ValorantWikiApp() {
    ValorantappTheme {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route ?: ""

        Scaffold(
            bottomBar = {
                if (currentRoute != InitialPageRoute.route) {
                    BottomAppBarNav(navController, currentRoute)
                }
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = InitialPageRoute.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(InitialPageRoute.route) {
                    InitialScreen(
                        navigateToHomePage = {
                            navController.navigate(HomePageRoute.route)
                        }
                    )
                }
                composable(HomePageRoute.route) {
                    HomePageScreen(navController)
                }
                composable(AgentRoute.route) {
                    AgentsScreen(navController)
                }
                composable(WeaponRoute.route) {
                    SkinsScreen(navController)
                }
            }
        }
    }
}
