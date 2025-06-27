package com.example.valorant_app.data.services

import com.example.valorant_app.data.entities.AgentsCard
import com.example.valorant_app.data.entities.WeaponCard
import com.example.valorant_app.utils.AGENTS_API_URL
import com.example.valorant_app.utils.WEAPONS_API_URL
import retrofit2.http.GET

interface ApiService {
    @GET(AGENTS_API_URL)
    suspend fun getAllAgentsCard() : List<AgentsCard>

    @GET(WEAPONS_API_URL)
    suspend fun getWeaponCard() : List<WeaponCard>
}