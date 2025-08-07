package com.example.valorant_app.ui.pages.weapon.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorant_app.data.repository.WeaponRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponListViewModel @Inject constructor(
    private val weaponRepository: WeaponRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeaponListUiState>(WeaponListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val response = weaponRepository.getAllWeaponCard()
            if (response.status == 200) {
                _uiState.value = WeaponListUiState.Success(response.data)
            } else {
                _uiState.value = WeaponListUiState.Error("Erro: ${response.status}")
            }
        }
    }
}