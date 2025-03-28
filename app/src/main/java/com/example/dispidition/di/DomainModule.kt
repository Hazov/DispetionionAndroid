package com.example.dispidition.di

import com.example.domain.repository.PersonRepository
import com.example.domain.repository.TripRepository
import com.example.domain.repository.TruckRepository
import com.example.domain.usecase.person.GetPersonUseCase
import com.example.domain.usecase.person.GetPersonsUseCase
import com.example.domain.usecase.trip.GetTripUseCase
import com.example.domain.usecase.trip.GetTripsUseCase
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

    @Provides
    fun providesGetTripsUseCase(tripRepository: TripRepository): GetTripsUseCase {
        return GetTripsUseCase(tripRepository)
    }

    @Provides
    fun providesGetTripUseCase(tripRepository: TripRepository): GetTripUseCase {
        return GetTripUseCase(tripRepository)
    }

    @Provides
    fun providesGetPersonsUseCase(personRepository: PersonRepository): GetPersonsUseCase {
        return GetPersonsUseCase(personRepository)
    }

    @Provides
    fun providesGetPersonUseCase(personRepository: PersonRepository): GetPersonUseCase {
        return GetPersonUseCase(personRepository)
    }
}