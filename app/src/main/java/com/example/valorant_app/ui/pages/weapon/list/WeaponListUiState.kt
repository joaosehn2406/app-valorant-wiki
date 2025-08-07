package com.example.valorant_app.ui.pages.weapon.list

import com.example.valorant_app.data.entities.card.WeaponCard

sealed class WeaponListUiState {
    object Loading : WeaponListUiState()
    data class Success(val weapons: List<WeaponCard>) : WeaponListUiState()
    data class Error(val message: String) : WeaponListUiState()

}