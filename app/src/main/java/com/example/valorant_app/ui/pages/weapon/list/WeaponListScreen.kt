package com.example.valorant_app.ui.pages.weapon.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.valorant_app.data.entities.card.WeaponCard
import com.example.valorant_app.ui.reusable_comp.BottomAppBarNav
import com.example.valorant_app.ui.reusable_comp.WeaponTopBar

@Composable
fun WeaponListScreen(
    navController: NavController,
    currentRoute: String,
    weaponScreenViewModel: WeaponListViewModel = hiltViewModel()
) {
    val state by weaponScreenViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            WeaponTopBar(navController)
        },
        bottomBar = {
            BottomAppBarNav(navController = navController, currentRoute = currentRoute)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            when (state) {
                is WeaponListUiState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primaryContainer)
                    }
                }

                is WeaponListUiState.Error -> {
                    Text(
                        (state as WeaponListUiState.Error).message,
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize()
                    )
                }

                is WeaponListUiState.Success -> {
                    val weapons = (state as WeaponListUiState.Success).weapons

                    WeaponListContent(
                        paddingValues = paddingValues,
                        navController = navController,
                        weapons = weapons
                    )
                }
            }
        }
    }
}

@Composable
fun WeaponListContent(
    paddingValues: PaddingValues,
    navController: NavController,
    weapons: List<WeaponCard>
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(paddingValues),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(items = weapons.sortedBy {
            it.displayName
        }) { weapon ->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .clickable {
                            navController.navigate("WeaponSingleRoute/${weapon.uuid}")
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        AsyncImage(
                            model = weapon.displayIcon,
                            contentDescription = weapon.displayName,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(bottom = 12.dp),
                            contentScale = ContentScale.Fit
                        )

                        Text(
                            text = weapon.displayName,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}