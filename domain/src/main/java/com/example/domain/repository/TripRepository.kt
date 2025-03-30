package com.example.domain.repository

import com.example.domain.model.trip.details.TripDetails
import com.example.domain.model.trip.registry.RegistryTrip

interface TripRepository {
    suspend fun GetTrip(id: Long): TripDetails;
    suspend fun GetTrips(): List<RegistryTrip>;
}