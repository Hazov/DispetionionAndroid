package com.example.data.storage

import com.example.data.model.trip.create.CreateTripRequest
import com.example.data.model.trip.create.CreateTripResponse
import com.example.data.model.trip.details.TripDetailsResponse
import com.example.data.model.trip.forDriver.changeStatus.request.ChangeStatusRequest
import com.example.data.model.trip.forDriver.changeStatus.response.ChangePointStatusResponse
import com.example.data.model.trip.forDriver.tripRoute.TripRouteResponse
import com.example.data.model.trip.gps.TripGpsDataResponse
import com.example.data.model.trip.registry.TripsRegistryResponse


interface TripStorage {
    suspend fun getTrip(id: Long) : TripDetailsResponse;
    suspend fun getTrips(): TripsRegistryResponse;
    suspend fun createTrip(request: CreateTripRequest): CreateTripResponse;
    suspend fun getTripRouteForDriver(): TripRouteResponse;
    suspend fun changePointStatus(changeStatusRequest: ChangeStatusRequest): ChangePointStatusResponse;
    suspend fun getTripGpsData(tripId:Long): TripGpsDataResponse
}