package com.example.domain.usecase.trip.get_trip


import com.example.domain.model.trip.details.TripDetails
import com.example.domain.repository.TripRepository

class GetTripUseCase(private val tripRepository: TripRepository) {
    suspend fun execute(id: Long) : TripDetails {
        return tripRepository.getTrip(id);
    }
}