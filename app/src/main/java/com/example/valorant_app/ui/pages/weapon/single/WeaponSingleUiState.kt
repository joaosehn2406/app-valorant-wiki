package com.example.valorant_app.ui.pages.weapon.single

import com.example.valorant_app.data.entities.single.WeaponSingle

sealed class WeaponSingleUiState {
    object Loading : WeaponSingleUiState()
    data class Success(val weapon: WeaponSingle) : WeaponSingleUiState()
    data class Error(val message: String) : WeaponSingleUiState()
}