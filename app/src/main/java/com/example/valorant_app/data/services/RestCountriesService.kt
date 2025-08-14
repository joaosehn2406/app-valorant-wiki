package com.example.valorant_app.data.services

import com.example.valorant_app.data.entities.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesService {

    @GET("v3.1/name/{country}")
    suspend fun getCountryFlagsByName(@Path("country") countryName: String): List<CountryResponse>
}