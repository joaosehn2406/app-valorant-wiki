package com.example.valorant_app.data.hiltmodules

import com.example.valorant_app.data.services.ValorantApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object ValorantNetworkModule {

    @Provides
    @Named("valorant")
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://valorant-api.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(@Named("valorant") retrofit: Retrofit): ValorantApiService {
        return retrofit.create(ValorantApiService::class.java)
    }
}
