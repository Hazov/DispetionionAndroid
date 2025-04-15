package com.example.dispidition.di

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.example.dispidition.presentation.screens.auth.LoginScreen
import com.example.dispidition.presentation.screens.person.CreatePersonScreen
import com.example.dispidition.presentation.screens.person.PersonDetailsScreen
import com.example.dispidition.presentation.screens.person.PersonsRegistryScreen
import com.example.dispidition.presentation.screens.trip.CreateTripScreen
import com.example.dispidition.presentation.screens.trip.TripDetailsScreen
import com.example.dispidition.presentation.screens.trip.TripsRegistryScreen
import com.example.dispidition.presentation.screens.trip.forDriver.TripRouteScreen
import com.example.dispidition.presentation.screens.truck.CreateTruckScreen
import com.example.dispidition.presentation.screens.truck.TruckDetailsScreen
import com.example.dispidition.presentation.screens.truck.TrucksRegistryScreen
import com.example.ui.auth.AuthUI
import com.example.ui.autocomplete.AutoComplete
import com.example.ui.details.DetailsUI
import com.example.ui.entites.trip.TripUI
import com.example.ui.registry.RegistryUI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import main.java.com.example.ui.create.CreateUI
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UIModule {

    @Provides
    @Singleton
    fun providesNavController(@ApplicationContext context: Context): NavHostController {
        return NavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            navigatorProvider.addNavigator(DialogNavigator())
        }
    }

    @Provides
    @Singleton
    fun providesCreateUI(): CreateUI {
        return CreateUI()
    }

    @Provides
    @Singleton
    fun providesAutocomplete(): AutoComplete {
        return AutoComplete()
    }

    @Provides
    @Singleton
    fun providesRegistryUI(): RegistryUI {
        return RegistryUI()
    }

    @Provides
    @Singleton
    fun providesAuthUI(): AuthUI {
        return AuthUI()
    }

    @Provides
    @Singleton
    fun providesDetailsUI(): DetailsUI {
        return DetailsUI()
    }

    @Provides
    @Singleton
    fun providesTripUI(): TripUI {
        return TripUI()
    }

    @Provides
    @Singleton
    fun providesCreatePersonScreen(
        createUI: CreateUI,
        navController: NavHostController
    ): CreatePersonScreen {
        return CreatePersonScreen(createUI, navController)
    }

    @Provides
    @Singleton
    fun providesTrucksRegistryScreen(
        createUI: CreateUI,
        registryUI: RegistryUI,
        navController: NavHostController
    ): TrucksRegistryScreen {
        return TrucksRegistryScreen(createUI, registryUI, navController)
    }

    @Provides
    @Singleton
    fun providesTruckDetailsScreen(detailsUI: DetailsUI, navController: NavHostController): TruckDetailsScreen {
        return TruckDetailsScreen(detailsUI, navController)
    }

    @Provides
    @Singleton
    fun providesCreateTruckScreen(
        createUI: CreateUI,
        navController: NavHostController
    ): CreateTruckScreen {
        return CreateTruckScreen(createUI, navController)
    }

    @Provides
    @Singleton
    fun providesTripsRegistryScreen(createUI: CreateUI, registryUI: RegistryUI, navController: NavHostController): TripsRegistryScreen {
        return TripsRegistryScreen(createUI, registryUI, navController)
    }

    @Provides
    @Singleton
    fun providesTripDetailsScreen(detailsUI: DetailsUI, tripUI: TripUI, navController: NavHostController): TripDetailsScreen {
        return TripDetailsScreen(detailsUI, tripUI, navController)
    }

    @Provides
    @Singleton
    fun providesCreateTripScreen(
        createUI: CreateUI,
        autoComplete: AutoComplete,
        navController: NavHostController
    ): CreateTripScreen {
        return CreateTripScreen(createUI, autoComplete, navController)
    }

    @Provides
    @Singleton
    fun providesTripRouteScreen(detailsUI: DetailsUI, tripUI: TripUI, navController: NavHostController): TripRouteScreen {
        return TripRouteScreen(detailsUI, tripUI, navController)
    }

    @Provides
    @Singleton
    fun providesPersonsRegistryScreen(
        createUI: CreateUI,
        registryUI: RegistryUI,
        navController: NavHostController
    ): PersonsRegistryScreen {
        return PersonsRegistryScreen(createUI, registryUI, navController)
    }

    @Provides
    @Singleton
    fun providesPersonDetailsScreen(detailsUI: DetailsUI, navController: NavHostController): PersonDetailsScreen {
        return PersonDetailsScreen(detailsUI, navController)
    }

    @Provides
    @Singleton
    fun providesLoginScreen(authUI: AuthUI, createUI: CreateUI, navController: NavHostController): LoginScreen {
        return LoginScreen(authUI, createUI, navController)
    }
}