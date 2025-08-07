package com.example.valorant_app.ui

import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.valorant_app.ui.pages.agent.list.compose.AgentListViewModel
import com.example.valorant_app.ui.pages.agent.list.compose.AgentsListScreen
import com.example.valorant_app.ui.pages.agent.list.xml.AgentsXmlFragment
import com.example.valorant_app.ui.pages.agent.single.AgentSingleScreen
import com.example.valorant_app.ui.pages.home.HomeContent
import com.example.valorant_app.ui.pages.initial_screen.InitialScreen
import com.example.valorant_app.ui.pages.weapon.list.WeaponListScreen
import com.example.valorant_app.ui.pages.weapon.single.WeaponSingleScreen
import com.example.valorant_app.ui.theme.AppTheme

@Composable
fun ValorantWikiApp() {
    AppTheme {
        val navController = rememberNavController()
        val agentScreenViewModel: AgentListViewModel = hiltViewModel()

        NavHost(navController, startDestination = InitialPageRoute.route) {
            composable(InitialPageRoute.route) {
                InitialScreen(navigateToHomePage = { navController.navigate(HomePageRoute.route) })
            }

            composable(HomePageRoute.route) {
                HomeContent(
                    navController = navController,
                    currentRoute = HomePageRoute.route,
                    agentScreenViewModel = agentScreenViewModel
                )
            }

            composable(AgentRoute.route) {
                AgentsListScreen(
                    navController = navController,
                    viewModel = agentScreenViewModel,
                    currentRoute = AgentRoute.route
                )
            }

            composable(WeaponRoute.route) {
                WeaponListScreen(navController, WeaponRoute.route)
            }

            composable("AgentSingleRoute/{uuid}") { backStack ->
                val id = backStack.arguments?.getString("uuid") ?: return@composable
                AgentSingleScreen(
                    agentId = id,
                    currentRoute = AgentRoute.route,
                    navController = navController
                )
            }

            composable("WeaponSingleRoute/{uuid}") { backStack ->
                val id = backStack.arguments?.getString("uuid") ?: return@composable
                WeaponSingleScreen(
                    weaponId = id,
                    currentRoute = WeaponRoute.route,
                    navController = navController
                )
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