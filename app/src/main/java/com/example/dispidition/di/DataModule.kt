package com.example.dispidition.di

import com.example.data.repo.PersonRepositoryImpl
import com.example.data.repo.TripRepositoryImpl
import com.example.data.repo.TruckRepositoryImpl
import com.example.data.storage.PersonStorage
import com.example.data.storage.TripStorage
import com.example.data.storage.TruckStorage
import com.example.data.storage.server.ServerPersonStorage
import com.example.data.storage.server.ServerTripStorage
import com.example.data.storage.server.ServerTruckStorage
import com.example.domain.repository.PersonRepository
import com.example.domain.repository.TripRepository
import com.example.domain.repository.TruckRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideTruckRepository(): TruckRepository {
        return TruckRepositoryImpl(provideServerTruckStorage())
    }

    @Provides
    @Singleton
    fun provideTripRepository(): TripRepository {
        return TripRepositoryImpl(provideServerTripStorage())
    }

    @Provides
    @Singleton
    fun providePersonRepository(): PersonRepository {
        return PersonRepositoryImpl(provideServerPersonStorage())
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.56.1:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideServerPersonStorage(): PersonStorage {
        return provideRetrofit().create(ServerPersonStorage::class.java)
    }

    @Provides
    @Singleton
    fun provideServerTripStorage(): TripStorage {
        return provideRetrofit().create(ServerTripStorage::class.java)
    }

    @Provides
    @Singleton
    fun provideServerTruckStorage(): TruckStorage {
        return provideRetrofit().create(ServerTruckStorage::class.java)
    }
}