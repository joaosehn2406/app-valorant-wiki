package com.example.valorant_app.data.services

import com.example.valorant_app.data.entities.AgentCard
import com.example.valorant_app.data.entities.CountryResponse
import com.example.valorant_app.data.entities.WeaponCard

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("agents")
    suspend fun getAllAgentsCard(): ApiResponse<List<AgentCard>>

    @GET("weapon")
    suspend fun getAllWeaponCard(): ApiResponse<List<WeaponCard>>

    @GET("v3.1/name/{country}")
    suspend fun getCountryFlagsByName(@Path("country") countryName: String): List<CountryResponse>
}
