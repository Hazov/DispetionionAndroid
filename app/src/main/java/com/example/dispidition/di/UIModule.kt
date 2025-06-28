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
import com.example.dispidition.app.global.GlobalSettings
import com.example.dispidition.presentation.screens.trip.TripGPSReportScreen
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
        globalSettings: GlobalSettings,
        navController: NavHostController
    ): CreatePersonScreen {
        return CreatePersonScreen(createUI, globalSettings, navController)
    }

    @Provides
    @Singleton
    fun providesTrucksRegistryScreen(
        createUI: CreateUI,
        registryUI: RegistryUI,
        globalSettings: GlobalSettings,
        navController: NavHostController
    ): TrucksRegistryScreen {
        return TrucksRegistryScreen(createUI, registryUI, globalSettings, navController)
    }

    @Provides
    @Singleton
    fun providesTruckDetailsScreen(
        detailsUI: DetailsUI,
        globalSettings: GlobalSettings,
        navController: NavHostController
    ): TruckDetailsScreen {
        return TruckDetailsScreen(detailsUI, globalSettings, navController)
    }

    @Provides
    @Singleton
    fun providesCreateTruckScreen(
        createUI: CreateUI, globalSettings: GlobalSettings,
        navController: NavHostController
    ): CreateTruckScreen {
        return CreateTruckScreen(createUI, globalSettings, navController)
    }

    @Provides
    @Singleton
    fun providesTripsRegistryScreen(
        createUI: CreateUI,
        registryUI: RegistryUI,
        globalSettings: GlobalSettings,
        navController: NavHostController
    ): TripsRegistryScreen {
        return TripsRegistryScreen(createUI, registryUI, globalSettings, navController)
    }

    @Provides
    @Singleton
    fun providesTripDetailsScreen(
        detailsUI: DetailsUI,
        tripUI: TripUI,
        globalSettings: GlobalSettings,
        navController: NavHostController
    ): TripDetailsScreen {
        return TripDetailsScreen(detailsUI, tripUI, globalSettings, navController)
    }

    @Provides
    @Singleton
    fun providesCreateTripScreen(
        createUI: CreateUI,
        autoComplete: AutoComplete,
        globalSettings: GlobalSettings,
        navController: NavHostController
    ): CreateTripScreen {
        return CreateTripScreen(createUI, autoComplete, globalSettings, navController)
    }

    @Provides
    @Singleton
    fun providesTripRouteScreen(
        detailsUI: DetailsUI,
        tripUI: TripUI,
        globalSettings: GlobalSettings,
        navController: NavHostController
    ): TripRouteScreen {
        return TripRouteScreen(detailsUI, tripUI, globalSettings, navController)
    }

    @Provides
    @Singleton
    fun providesPersonsRegistryScreen(
        createUI: CreateUI,
        registryUI: RegistryUI,
        globalSettings: GlobalSettings,
        navController: NavHostController
    ): PersonsRegistryScreen {
        return PersonsRegistryScreen(createUI, registryUI, globalSettings, navController)
    }

    @Provides
    @Singleton
    fun providesPersonDetailsScreen(
        detailsUI: DetailsUI,
        globalSettings: GlobalSettings,
        navController: NavHostController
    ): PersonDetailsScreen {
        return PersonDetailsScreen(detailsUI, globalSettings, navController)
    }

    @Provides
    @Singleton
    fun providesLoginScreen(
        authUI: AuthUI,
        createUI: CreateUI,
        navController: NavHostController
    ): LoginScreen {
        return LoginScreen(authUI, createUI, navController)
    }

    @Provides
    @Singleton
    fun providesTripGPSReportScreen(
        detailsUI: DetailsUI,
        globalSettings: GlobalSettings,
        navController: NavHostController
    ): TripGPSReportScreen {
        return TripGPSReportScreen(detailsUI, globalSettings, navController)
    }
}