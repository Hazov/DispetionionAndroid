package com.example.dispidition.di

import com.example.data.repo.TruckRepositoryImpl
import com.example.domain.repository.TruckRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideTruckRepository(): TruckRepository {
        return TruckRepositoryImpl()
    }
}