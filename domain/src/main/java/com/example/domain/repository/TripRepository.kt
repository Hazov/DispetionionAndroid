package com.example.domain.repository

import com.example.domain.model.trip.create.CreateTripResponse
import com.example.domain.model.trip.create.NewTrip
import com.example.domain.model.trip.details.TripDetails
import com.example.domain.model.trip.forDriver.changeStatus.ChangePointStatusResponse
import com.example.domain.model.trip.forDriver.tripRoute.TripRoute
import com.example.domain.model.trip.registry.RegistryTrip

interface TripRepository {
    suspend fun getTrip(id: Long): TripDetails;
    suspend fun getTrips(): List<RegistryTrip>;
    suspend fun createTrip(newTrip: NewTrip): CreateTripResponse;
    suspend fun getTripRouteForDriver(): TripRoute;
    suspend fun changePointStatus(id: Long): ChangePointStatusResponse;
}