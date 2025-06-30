package com.example.valorant_app.data.repository

import com.example.valorant_app.data.entities.CountryResponse
import com.example.valorant_app.data.services.ApiService
import javax.inject.Inject

interface CountryFlagRepository {
    suspend fun getCountryFlagsByName(countryName: String): List<CountryResponse>
}

class CountryFlagRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CountryFlagRepository {

    override suspend fun getCountryFlagsByName(countryName: String): List<CountryResponse> {
        return try {
            apiService.getCountryFlagsByName(countryName)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
