package com.example.valorant_app.data

import com.example.valorant_app.ui.pages.weapon.single.WeaponSingleViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface WeaponSingleViewModelEntryPoint {
    fun weaponSingleViewModelFactory(): WeaponSingleViewModel.Factory
}