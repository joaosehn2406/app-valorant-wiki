package com.example.valorant_app.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.valorant_app.ui.reusable_comp.BottomAppBarNav

@Composable
fun HomePageScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    BottomAppBarNav(
        navController = navController,
        modifier = modifier.padding(top = 400.dp)
    )
}

