package com.example.valorant_app.ui.pages.skin

import com.example.valorant_app.data.entities.WeaponCard

sealed class WeaponUiState {
    object Loading : WeaponUiState()
    data class Success(val weapons: List<WeaponCard>) : WeaponUiState()
    data class Error(val message: String) : WeaponUiState()

}