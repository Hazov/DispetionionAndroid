package com.example.domain.usecase.trip


import com.example.domain.model.trip.TripDetails
import com.example.domain.repository.TripRepository

class GetTripUseCase(private val tripRepository: TripRepository) {
    suspend fun execute(id: Long) : TripDetails {
        return tripRepository.GetTrip(id);
    }
}