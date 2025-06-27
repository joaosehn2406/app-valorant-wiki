package com.example.valorant_app.data.hiltmodules

import com.example.valorant_app.data.repository.AgentRepository
import com.example.valorant_app.data.repository.AgentRepositoryImpl
import com.example.valorant_app.data.repository.WeaponRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindAgentRepository(agentRepositoryImpl: AgentRepositoryImpl): AgentRepository

    @Binds
    abstract fun bindWeaponRepository(weaponRepositoryImpl: WeaponRepositoryImpl): WeaponRepository

}