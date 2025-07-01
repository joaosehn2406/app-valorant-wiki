package com.example.valorant_app.ui.pages.skin

import androidx.lifecycle.ViewModel
import com.example.valorant_app.data.repository.WeaponRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SkinsScreenViewModel @Inject constructor(
    private val weaponRepository: WeaponRepository
) : ViewModel(){

}