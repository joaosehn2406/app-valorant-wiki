package com.example.valorant_app.ui.pages.weapon.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorant_app.data.repository.WeaponRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkinsScreenViewModel @Inject constructor(
    private val weaponRepository: WeaponRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow<WeaponUiState>(WeaponUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val response = weaponRepository.getAllWeaponCard()
            if (response.status == 200) {
                _uiState.value = WeaponUiState.Success(response.data)
            } else {
                _uiState.value = WeaponUiState.Error("Erro: ${response.status}")
            }
        }
    }

}