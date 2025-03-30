package com.example.data.repo


import com.example.data.storage.TripStorage
import com.example.domain.model.trip.details.TripDetails
import com.example.domain.model.trip.registry.RegistryTrip
import com.example.domain.repository.TripRepository

class TripRepositoryImpl(private val tripStorage: TripStorage) : TripRepository {
    override suspend fun GetTrip(id: Long): TripDetails {
        return tripStorage.getTrip(id).tripDetails.toDomainTripDetails()
    }

    override suspend fun GetTrips(): List<RegistryTrip> {
        return tripStorage.getTrips().trips.map { trip -> trip.toDomainTripDetails() }
    }
}

