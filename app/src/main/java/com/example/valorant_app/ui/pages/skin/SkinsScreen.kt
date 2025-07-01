package com.example.valorant_app.ui.pages.skin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.valorant_app.data.utils.getCountryInfo
import com.example.valorant_app.ui.theme.ValorantRed

@Composable
fun SkinsScreen(
    skinsScreenViewModel: SkinsScreenViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val state by skinsScreenViewModel.uiState.collectAsStateWithLifecycle()

    when (state) {
        is WeaponUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = ValorantRed)
            }
        }

        is WeaponUiState.Error -> {
            Text(
                (state as WeaponUiState.Error).message,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }

        is WeaponUiState.Success -> {
            val weapons = (state as WeaponUiState.Success).weapons

            LazyColumn {
                items(items = weapons) { weapon ->
                    Spacer(modifier = Modifier.height(5.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = weapon.displayIcon ?: "",
                                contentDescription = weapon.displayName,
                                modifier = Modifier.size(56.dp)
                            )

                        }
                    }

                }
            }
        }
    }
}