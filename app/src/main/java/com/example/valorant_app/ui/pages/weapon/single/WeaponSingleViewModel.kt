package com.example.valorant_app.ui.pages.weapon.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorant_app.data.repository.WeaponRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeaponSingleViewModel @AssistedInject constructor(
    private val weaponSingleRepository: WeaponRepository,
    @Assisted private val weaponId: String
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeaponSingleUiState>(WeaponSingleUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchWeaponSingle()
    }

    private fun fetchWeaponSingle() {
        viewModelScope.launch {
            try {
                val response = weaponSingleRepository.getWeaponById(weaponId)
                if (response.status == 200) {
                    _uiState.value = WeaponSingleUiState.Success(response.data)
                } else {
                    _uiState.value = WeaponSingleUiState.Error("Erro: ${response.status}")
                }
            } catch (e: Exception) {
                _uiState.value = WeaponSingleUiState.Error("Erro: ${e.message}")
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(weaponId: String): WeaponSingleViewModel
    }
}
