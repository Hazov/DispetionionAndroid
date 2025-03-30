package com.example.data.storage

import com.example.data.model.trip.details.TripDetailsResponse
import com.example.data.model.trip.registry.TripsRegistryResponse


interface TripStorage {
    suspend fun getTrip(id: Long) : TripDetailsResponse;
    suspend fun getTrips(): TripsRegistryResponse;
}