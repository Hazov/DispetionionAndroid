package com.example.domain.repository

import com.example.domain.model.trip.create.CreateTripResponse
import com.example.domain.model.trip.create.NewTrip
import com.example.domain.model.trip.details.TripDetails
import com.example.domain.model.trip.forDriver.changeStatus.ChangePointStatusResponse
import com.example.domain.model.trip.forDriver.changeStatus.ChangeTripPointStatus
import com.example.domain.model.trip.forDriver.tripRoute.TripRoute
import com.example.domain.model.trip.registry.RegistryTrip
import com.example.domain.model.trip.tripgps.TripGps

interface TripRepository {
    suspend fun getTrip(id: Long): TripDetails;
    suspend fun getTrips(): List<RegistryTrip>;
    suspend fun createTrip(newTrip: NewTrip): CreateTripResponse;
    suspend fun getTripRouteForDriver(): TripRoute;
    suspend fun changePointStatus(changeTripPointStatus:ChangeTripPointStatus): ChangePointStatusResponse;
    suspend fun getTripGpsData(tripId:Long): List<TripGps>
}