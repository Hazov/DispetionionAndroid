package com.example.dispidition.di

import com.example.data.repo.PersonRepositoryImpl
import com.example.data.repo.TripRepositoryImpl
import com.example.data.repo.TruckRepositoryImpl
import com.example.domain.repository.PersonRepository
import com.example.domain.repository.TripRepository
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

    @Provides
    @Singleton
    fun provideTripRepository(): TripRepository {
        return TripRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providePersonRepository(): PersonRepository {
        return PersonRepositoryImpl()
    }
}