package com.example.valorant_app.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valorant_app.ui.navigation.AgentRoute
import com.example.valorant_app.ui.navigation.HomePageRoute
import com.example.valorant_app.ui.navigation.InitialPageRoute
import com.example.valorant_app.ui.navigation.WeaponRoute
import com.example.valorant_app.ui.pages.agent.card.AgentScreenViewModel
import com.example.valorant_app.ui.pages.agent.card.AgentsScreen
import com.example.valorant_app.ui.pages.agent.single.AgentSingleScreen
import com.example.valorant_app.ui.pages.home.HomeContent
import com.example.valorant_app.ui.pages.initial_screen.InitialScreen
import com.example.valorant_app.ui.pages.weapon.card.WeaponSkinsScreen
import com.example.valorant_app.ui.pages.weapon.single.WeaponSingleScreen
import com.example.valorant_app.ui.reusable_comp.AgentSingleTopBar
import com.example.valorant_app.ui.reusable_comp.AgentTopBar
import com.example.valorant_app.ui.reusable_comp.AppScaffold
import com.example.valorant_app.ui.reusable_comp.HomeTopBar
import com.example.valorant_app.ui.reusable_comp.WeaponSingleTopBar
import com.example.valorant_app.ui.reusable_comp.WeaponTopBar

@Composable
fun ValorantWikiApp() {
    val navController = rememberNavController()
    val agentScreenViewModel: AgentScreenViewModel = hiltViewModel()
    NavHost(navController, startDestination = InitialPageRoute.route) {

        composable(InitialPageRoute.route) {
            InitialScreen(
                navigateToHomePage = { navController.navigate(HomePageRoute.route) }
            )
        }

        composable(HomePageRoute.route) {
            AppScaffold(
                navController,
                HomePageRoute.route,
                topBar = { HomeTopBar(navController, agentScreenViewModel) }
            ) { padding ->
                HomeContent(modifier = padding)
            }
        }

        composable(AgentRoute.route) {
            AppScaffold(
                navController,
                AgentRoute.route,
                topBar = { AgentTopBar(navController) }
            ) { padding ->
                AgentsScreen(navController, modifier = padding, agentScreenViewModel = agentScreenViewModel)
            }
        }


        composable(WeaponRoute.route) {
            AppScaffold(
                navController,
                WeaponRoute.route,
                topBar = { WeaponTopBar(navController) }
            ) { padding ->
                WeaponSkinsScreen(navController, modifier = padding)
            }
        }

        composable("AgentSingleRoute/{uuid}") { backStack ->
            val id = backStack.arguments?.getString("uuid") ?: return@composable
            AppScaffold(
                navController,
                AgentRoute.route,
                topBar = { AgentSingleTopBar(navController) }
            ) { padding ->
                AgentSingleScreen(agentId = id, navController = navController, modifier = padding)
            }
        }

        composable("WeaponSingleRoute/{uuid}") { backStack ->
            val id = backStack.arguments?.getString("uuid") ?: return@composable
            AppScaffold(
                navController,
                WeaponRoute.route,
                topBar = { WeaponSingleTopBar(navController) }
            ) { padding ->
                WeaponSingleScreen(weaponId = id, navController = navController, modifier = padding)
            }
        }
    }
}

