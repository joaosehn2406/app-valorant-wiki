//package com.example.valorant_app.ui.pages
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Build
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.rememberNavController
//import com.example.valorant_app.R
//import com.example.valorant_app.ui.destinations.AgentsRoute
//import com.example.valorant_app.ui.destinations.GunsRoute
//import com.example.valorant_app.ui.destinations.HomePageRoute
//
//@Composable
//fun HomePageScreen(modifier: Modifier = Modifier) {
//
//}
//
//@Composable
//fun BottomAppBarNav(modifier: Modifier = Modifier) {
//    val navController = rememberNavController()
//
//    Scaffold(
//        modifier = Modifier,
//        bottomBar = {
//            BottomAppBar {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    IconButton(
//                        onClick = { navController.navigate(HomePageRoute.toString()) }
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_agents_icon),
//                            contentDescription = "Icone agentes",
//                            modifier = Modifier.size(24.dp)
//                        )
//                    }
//                    IconButton(
//                        onClick = { navController.navigate(GunsRoute.toString()) }
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.chatgpt_image_27_de_jun__de_2025__08_39_54),
//                            contentDescription = "Icone Armas",
//                            modifier = Modifier.size(24.dp)
//                        )
//                    }
//                }
//            }
//        }
//    ) { innerPadding ->
//
//        NavHost(
//            navController = navController,
//            startDestination = HomePageRoute.toString(),
//            modifier = Modifier.padding(innerPadding)
//
//        ) {
//            composable(HomePageRoute.toString()) {
//                HomePageScreen()
//            }
//        }
//
//    }
//}
//
//@Preview
//@Composable
//private fun HomeScreenPreview() {
//    HomePageScreen()
//}