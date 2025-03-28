package com.example.data.storage

import com.example.data.model.trip.TripDetailsResponse
import com.example.data.model.trip.TripsRegistryResponse


interface TripStorage {
    suspend fun getTrip(id: Long) : TripDetailsResponse;
    suspend fun getTrips(): TripsRegistryResponse;
}