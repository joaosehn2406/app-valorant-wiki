package com.example.valorant_app.ui.pages.weapon.single

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.valorant_app.data.repository.WeaponRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeaponSingleViewModel @Inject constructor(
    val weaponSingleRepository: WeaponRepository
) : ViewModel() {

}