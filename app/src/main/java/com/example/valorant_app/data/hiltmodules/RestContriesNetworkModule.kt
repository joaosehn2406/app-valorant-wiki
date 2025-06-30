// RestCountriesNetworkModule.kt
package com.example.valorant_app.data.hiltmodules

import com.example.valorant_app.data.services.RestCountriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestCountriesNetworkModule {
    @Provides
    @Singleton
    fun provideRestCountriesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestCountriesService(
        retrofit: Retrofit
    ): RestCountriesService =
        retrofit.create(RestCountriesService::class.java)
}
