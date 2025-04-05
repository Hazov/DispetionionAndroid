package com.example.dispidition.di

import android.content.Context
import com.example.domain.repository.PersonRepository
import com.example.domain.repository.TripRepository
import com.example.domain.repository.TruckRepository
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.person.CreatePersonUseCase
import com.example.domain.usecase.person.GetPersonUseCase
import com.example.domain.usecase.person.GetPersonsUseCase
import com.example.domain.usecase.trip.CreateTripUseCase
import com.example.domain.usecase.trip.GetTripUseCase
import com.example.domain.usecase.trip.GetTripsUseCase
import com.example.domain.usecase.trip.forDriver.GetTripRouteUseCase
import com.example.domain.usecase.truck.CreateTruckUseCase
import com.example.domain.usecase.truck.GetTruckUseCase
import com.example.domain.usecase.truck.GetTrucksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    // truck
    @Provides
    fun providesGetTruckUseCase(truckRepository: TruckRepository): GetTruckUseCase {
        return GetTruckUseCase(truckRepository)
    }

    @Provides
    fun providesGetTrucksUseCase(truckRepository: TruckRepository): GetTrucksUseCase {
        return GetTrucksUseCase(truckRepository)
    }

    @Provides
    fun providesCreateTruckUseCase(truckRepository: TruckRepository): CreateTruckUseCase {
        return CreateTruckUseCase(truckRepository)
    }
    
    
    

    //trip
    @Provides
    fun providesGetTripsUseCase(tripRepository: TripRepository): GetTripsUseCase {
        return GetTripsUseCase(tripRepository)
    }

    @Provides
    fun providesGetTripUseCase(tripRepository: TripRepository): GetTripUseCase {
        return GetTripUseCase(tripRepository)
    }

    @Provides
    fun providesCreateTripUseCase(tripRepository: TripRepository): CreateTripUseCase {
        return CreateTripUseCase(tripRepository)
    }

    @Provides
    fun providesGetTripRouteUseCase(tripRepository: TripRepository): GetTripRouteUseCase {
        return GetTripRouteUseCase(tripRepository)
    }

    @Provides
    fun providesChangePointStatusUseCase(tripRepository: TripRepository): ChangePointStatusUseCase {
        return ChangePointStatusUseCase(tripRepository)
    }


    
    
    
    
    //person
    @Provides
    fun providesGetPersonsUseCase(personRepository: PersonRepository): GetPersonsUseCase {
        return GetPersonsUseCase(personRepository)
    }

    @Provides
    fun providesGetPersonUseCase(personRepository: PersonRepository): GetPersonUseCase {
        return GetPersonUseCase(personRepository)
    }

    @Provides
    fun providesCreatePersonUseCase(personRepository: PersonRepository): CreatePersonUseCase {
        return CreatePersonUseCase(personRepository)
    }

    //auth
    @Provides
    fun provideLoginUseCase(@ApplicationContext context:Context, personRepository: PersonRepository): LoginUseCase {
        return LoginUseCase(context, personRepository)
    }

    
}