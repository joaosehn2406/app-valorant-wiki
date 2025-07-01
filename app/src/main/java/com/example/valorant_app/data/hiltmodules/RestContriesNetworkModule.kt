// RestCountriesNetworkModule.kt
package com.example.valorant_app.data.hiltmodules

import com.example.valorant_app.data.services.RestCountriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestCountriesNetworkModule {
    @Provides
    @Named("restCountries")
    fun provideRestCountriesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideRestCountriesService(@Named("restCountries")
        retrofit: Retrofit
    ): RestCountriesService =
        retrofit.create(RestCountriesService::class.java)
}
