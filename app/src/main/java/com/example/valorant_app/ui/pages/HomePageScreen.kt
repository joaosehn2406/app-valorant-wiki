package com.example.valorant_app.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.valorant_app.ui.reusable_comp.BottomAppBarNav

@Composable
fun HomePageScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            BottomAppBarNav(
                navController = navController,
                modifier = Modifier

            )
        }
    ) { innerPadding ->
        Text(text = "", modifier = modifier.padding(innerPadding))
    }
}
