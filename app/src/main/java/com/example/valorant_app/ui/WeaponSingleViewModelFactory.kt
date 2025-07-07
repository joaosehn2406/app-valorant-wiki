package com.example.valorant_app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.valorant_app.ui.pages.weapon.single.WeaponSingleViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class WeaponSingleViewModelFactory @AssistedInject constructor (
    private val assistedFactory: WeaponSingleViewModel.Factory,
    @Assisted private val weaponId: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(weaponId) as T
    }
}
