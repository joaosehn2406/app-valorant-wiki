package com.example.valorant_app.ui.pages.agent.card.compose

import androidx.lifecycle.ViewModel
import com.example.valorant_app.data.repository.CountryFlagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FlagViewModel @Inject constructor(
    private val countryRepo: CountryFlagRepository
) : ViewModel() {

    private val _flagUrl = MutableStateFlow<String?>(null)
    val flagUrl: StateFlow<String?> = _flagUrl

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

}