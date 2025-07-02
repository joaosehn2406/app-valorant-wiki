package com.example.valorant_app.ui.pages.weapon.card

import com.example.valorant_app.data.entities.card.WeaponCard

sealed class WeaponCardUiState {
    object Loading : WeaponCardUiState()
    data class Success(val weapons: List<WeaponCard>) : WeaponCardUiState()
    data class Error(val message: String) : WeaponCardUiState()

}