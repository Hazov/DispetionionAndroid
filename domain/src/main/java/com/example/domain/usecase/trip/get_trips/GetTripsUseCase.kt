package com.example.domain.usecase.trip.get_trips

import com.example.domain.model.trip.registry.RegistryTrip
import com.example.domain.repository.TripRepository

class GetTripsUseCase(private val tripRepository: TripRepository) {
    suspend fun execute(): List<RegistryTrip> {
        return tripRepository.getTrips()
    }
}