package com.example.dispidition.di

import com.example.dispidition.app.global.GlobalSettings
import com.example.domain.usecase.auth.FetchPermissionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesGlobalSettings() : GlobalSettings {
        return GlobalSettings()
    }
}