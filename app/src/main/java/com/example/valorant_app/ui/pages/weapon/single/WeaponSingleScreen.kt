package com.example.valorant_app.ui.pages.weapon.single

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.valorant_app.ui.theme.ValorantRed

@Composable
fun WeaponSingleScreen(
    weaponId: String,
    navController: NavController,
    weaponSingleViewModel: WeaponSingleViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    weaponSingleViewModel.fetchWeaponSingle(weaponId)

    val state = weaponSingleViewModel.uiState.collectAsStateWithLifecycle().value

    when (state) {
        is WeaponSingleUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0E0E10)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = ValorantRed)
            }
        }

        is WeaponSingleUiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0E0E10)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.message,
                    color = Color.Red,
                    modifier = Modifier.wrapContentSize()
                )
            }
        }
        is WeaponSingleUiState.Success -> {
            val weapon = state.weapon
        }
    }
}
