package com.example.valorant_app.ui.pages.weapon.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorant_app.data.repository.WeaponRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponSingleViewModel @Inject constructor(
    val weaponSingleRepository: WeaponRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeaponSingleUiState>(WeaponSingleUiState.Loading)
    private val uiState = _uiState.asStateFlow()

    fun fetchWeaponSingle(weaponId: String) {
        _uiState.value = WeaponSingleUiState.Loading

        viewModelScope.launch {
            val response = weaponSingleRepository.getWeaponById(weaponId)
            try {
                if (response.status == 200) {
                    _uiState.value = WeaponSingleUiState.Success(response.data)
                } else {
                    _uiState.value = WeaponSingleUiState.Error("Erro: ${response.status}")
                }
            } catch (e: Exception) {
                _uiState.value = WeaponSingleUiState.Error("Erro: ${response.status}")
            }
        }
    }
}