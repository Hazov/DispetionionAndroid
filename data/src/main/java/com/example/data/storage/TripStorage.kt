package com.example.data.storage

import com.example.data.model.trip.create.CreateTripRequest
import com.example.data.model.trip.create.CreateTripResponse
import com.example.data.model.trip.details.TripDetailsResponse
import com.example.data.model.trip.forDriver.changeStatus.ChangePointStatusRequest
import com.example.data.model.trip.forDriver.changeStatus.ChangePointStatusResponse
import com.example.data.model.trip.forDriver.tripRoute.TripRouteResponse
import com.example.data.model.trip.registry.TripsRegistryResponse


interface TripStorage {
    suspend fun getTrip(id: Long) : TripDetailsResponse;
    suspend fun getTrips(): TripsRegistryResponse;
    suspend fun createTrip(request: CreateTripRequest): CreateTripResponse;
    suspend fun getTripRouteForDriver(): TripRouteResponse;
    suspend fun changePointStatus(changePointStatusRequest: ChangePointStatusRequest): ChangePointStatusResponse;
}