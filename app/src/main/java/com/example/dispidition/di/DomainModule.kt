package com.example.dispidition.di

import android.content.Context
import com.example.dispidition.app.global.GlobalSettings
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.FirebaseRepository
import com.example.domain.repository.LocationRepository
import com.example.domain.repository.PersonRepository
import com.example.domain.repository.TripRepository
import com.example.domain.repository.TruckRepository
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.LogoutUseCase
import com.example.domain.usecase.auth.FetchPermissionsUseCase
import com.example.domain.usecase.auth.GetDeviceTokenUseCase
import com.example.domain.usecase.auth.GetPermissionsUseCase
import com.example.domain.usecase.gps.DefineGpsUseCase
import com.example.domain.usecase.person.CreatePersonUseCase
import com.example.domain.usecase.person.GetPersonUseCase
import com.example.domain.usecase.person.GetPersonsUseCase
import com.example.domain.usecase.trip.create.CreateTripUseCase
import com.example.domain.usecase.trip.get_trip.GetTripUseCase
import com.example.domain.usecase.trip.get_trips.GetTripsUseCase
import com.example.domain.usecase.trip.autocomplete.GetDriversAutoCompleteUseCase
import com.example.domain.usecase.trip.autocomplete.GetTrucksAutoCompleteUseCase
import com.example.domain.usecase.trip.forDriver.GetTripRouteUseCase
import com.example.domain.usecase.truck.CreateTruckUseCase
import com.example.domain.usecase.truck.GetTruckUseCase
import com.example.domain.usecase.truck.GetTrucksUseCase
import com.example.domain.usecase.trip.forDriver.ChangePointStatusUseCase
import com.example.domain.usecase.trip.get_trip_gps.GetTripGpsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

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

    @Provides
    fun provideGetTrucksAutoCompleteUseCase(truckRepository: TruckRepository): GetTrucksAutoCompleteUseCase {
        return GetTrucksAutoCompleteUseCase(truckRepository)
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

    @Provides
    fun providesGetTripGpsUseCase(tripRepository: TripRepository): GetTripGpsUseCase {
        return GetTripGpsUseCase(tripRepository)
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

    @Provides
    fun provideGetDriversAutoCompleteUseCase(personRepository: PersonRepository): GetDriversAutoCompleteUseCase {
        return GetDriversAutoCompleteUseCase(personRepository)
    }

    //auth
    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    fun provideLogoutUseCase(authRepository: AuthRepository): LogoutUseCase {
        return LogoutUseCase(authRepository)
    }

    @Provides
    fun provideFetchPermissionsUseCase(authRepository: AuthRepository): FetchPermissionsUseCase {
        return FetchPermissionsUseCase(authRepository)
    }

    @Provides
    fun provideGetPermissionsUseCase(authRepository: AuthRepository): GetPermissionsUseCase {
        return GetPermissionsUseCase(authRepository)
    }

    @Provides
    fun provideGetDeviceTokenUseCase(firebaseRepository: FirebaseRepository): GetDeviceTokenUseCase {
        return GetDeviceTokenUseCase(firebaseRepository)
    }


    //gps
    @Provides
    fun provideGetGpsUseCase(@ApplicationContext context: Context, locationRepository: LocationRepository): DefineGpsUseCase {
        return DefineGpsUseCase(context, locationRepository)
    }
}