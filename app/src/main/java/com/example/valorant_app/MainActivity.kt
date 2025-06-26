package com.example.valorant_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.valorant_app.ui.destinations.HomePageRoute
import com.example.valorant_app.ui.destinations.InitialPageRoute
import com.example.valorant_app.ui.pages.HomePageScreen
import com.example.valorant_app.ui.pages.InitialScreen
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

@Composable
fun ValorantWikiApp(modifier: Modifier = Modifier) {
    ValorantappTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = InitialPageRoute
        ) {
            composable<InitialPageRoute> {
                InitialScreen(
                    navigateToHomePage = {
                        navController.navigate(route = HomePageRoute)
                    },
                    modifier = Modifier
                )
            }

            composable<HomePageRoute> {
                HomePageScreen()
            }
        }
    }

}

