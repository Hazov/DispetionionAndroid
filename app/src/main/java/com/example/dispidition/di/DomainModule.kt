package com.example.dispidition.di

import com.example.domain.repository.TruckRepository
import com.example.domain.usecase.truck.GetTruckUseCase
import com.example.domain.usecase.truck.GetTrucksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun providesGetTruckUseCase(truckRepository: TruckRepository): GetTruckUseCase {
        return GetTruckUseCase(truckRepository)
    }

    @Provides
    fun providesGetTrucksUseCase(truckRepository: TruckRepository): GetTrucksUseCase {
        return GetTrucksUseCase(truckRepository)
    }
}