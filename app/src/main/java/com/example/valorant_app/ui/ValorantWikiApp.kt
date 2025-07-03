package com.example.valorant_app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valorant_app.R
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
import com.example.valorant_app.ui.reusable_comp.AppScaffold
import com.example.valorant_app.ui.reusable_comp.FlagsDropdown

@Composable
fun ValorantWikiApp() {
    val navController = rememberNavController()
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
                topBar = { HomeTopBar(navController) }
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
                AgentsScreen(navController, modifier = padding)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navController: NavController) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name), color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF0E0E10),
            titleContentColor = Color(0xFFE03240)
        ),
        actions = { FlagsDropdown() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentTopBar(navController: NavController) {
    TopAppBar(
        title = { Text(stringResource(R.string.agents), color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF0E0E10),
            titleContentColor = Color(0xFFE03240)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponTopBar(navController: NavController) {
    TopAppBar(
        title = { Text(stringResource(R.string.weapon), color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF0E0E10),
            titleContentColor = Color(0xFFE03240)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentSingleTopBar(navController: NavController) {
    TopAppBar(
        title = { Text(stringResource(R.string.agent_details), color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF0E0E10),
            titleContentColor = Color(0xFFE03240),
            navigationIconContentColor = Color.White
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponSingleTopBar(navController: NavController) {
    TopAppBar(
        title = { Text(stringResource(R.string.weapon_skins), color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF0E0E10),
            titleContentColor = Color(0xFFE03240),
            navigationIconContentColor = Color.White
        )
    )
}
