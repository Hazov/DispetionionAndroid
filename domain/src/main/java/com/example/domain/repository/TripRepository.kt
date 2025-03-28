package com.example.domain.repository

import com.example.domain.model.trip.RegistryTrip
import com.example.domain.model.trip.TripDetails

interface TripRepository {
    suspend fun GetTrip(id: Long): TripDetails;
    suspend fun GetTrips(): List<RegistryTrip>;
}