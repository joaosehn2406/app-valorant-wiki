package com.example.valorant_app.ui

import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commitNow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valorant_app.ui.navigation.AgentRoute
import com.example.valorant_app.ui.navigation.HomePageRoute
import com.example.valorant_app.ui.navigation.InitialPageRoute
import com.example.valorant_app.ui.navigation.WeaponRoute
import com.example.valorant_app.ui.pages.agent.card.compose.AgentScreenViewModel
import com.example.valorant_app.ui.pages.agent.card.compose.AgentsScreen
import com.example.valorant_app.ui.pages.agent.card.xml.AgentsXmlFragment
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
import com.example.valorant_app.ui.theme.AppTheme

@Composable
fun ValorantWikiApp() {
    AppTheme {
        val navController = rememberNavController()
        val agentScreenViewModel: AgentScreenViewModel = hiltViewModel()

        NavHost(navController, startDestination = InitialPageRoute.route) {
            composable(InitialPageRoute.route) {
                InitialScreen(navigateToHomePage = { navController.navigate(HomePageRoute.route) })
            }

            composable(HomePageRoute.route) {
                AppScaffold(
                    navController = navController,
                    currentRoute = HomePageRoute.route,
                    topBar = { HomeTopBar(navController, agentScreenViewModel) }) { padding ->
                    HomeContent(Modifier.padding(padding))
                }
            }

            composable(AgentRoute.route) {
                var showFilter by rememberSaveable { mutableStateOf(false) }

                AppScaffold(
                    navController = navController,
                    currentRoute = AgentRoute.route,
                    topBar = {
                        AgentTopBar(
                            onFilterClick = { showFilter = !showFilter },
                            navController = navController
                        )
                    }
                ) { padding ->
                    AgentsScreen(
                        modifier = Modifier.padding(padding),
                        navController = navController,
                        viewModel = agentScreenViewModel,
                        showFilter = showFilter
                    )
                }
            }

            composable(WeaponRoute.route) {
                AppScaffold(
                    navController = navController,
                    currentRoute = WeaponRoute.route,
                    topBar = { WeaponTopBar(navController) }) { padding ->
                    WeaponSkinsScreen(navController)
                }
            }

            composable("AgentSingleRoute/{uuid}") { backStack ->
                val id = backStack.arguments?.getString("uuid") ?: return@composable
                AppScaffold(
                    navController = navController,
                    currentRoute = AgentRoute.route,
                    topBar = { AgentSingleTopBar(navController) }) { padding ->
                    AgentSingleScreen(agentId = id, modifier = Modifier.padding(padding))
                }
            }

            composable("WeaponSingleRoute/{uuid}") { backStack ->
                val id = backStack.arguments?.getString("uuid") ?: return@composable
                AppScaffold(
                    navController = navController,
                    currentRoute = WeaponRoute.route,
                    topBar = { WeaponSingleTopBar(navController) }) { padding ->
                    WeaponSingleScreen(weaponId = id)
                }
            }

            composable("agentsXml") {
                val context = LocalContext.current
                val fragmentXml = remember { (context as FragmentActivity).supportFragmentManager }
                val containerId = remember { View.generateViewId() }

                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { ctx ->
                        FragmentContainerView(ctx).apply { id = containerId }
                    },
                    update = { view ->
                        if (fragmentXml.findFragmentById(containerId) == null) {
                            val fragment = AgentsXmlFragment().apply {
                                onAgentSelected = { uuid ->
                                    navController.navigate("AgentSingleRoute/$uuid")
                                }
                            }
                            fragmentXml.commitNow { replace(containerId, fragment) }
                        }
                    }
                )
            }
        }
    }
}
