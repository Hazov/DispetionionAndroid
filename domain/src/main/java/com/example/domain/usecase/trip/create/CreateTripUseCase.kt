package com.example.domain.usecase.trip.create

import com.example.domain.model.trip.create.CreateTripResponse
import com.example.domain.model.trip.create.NewTrip
import com.example.domain.repository.TripRepository

class CreateTripUseCase(private val tripRepository: TripRepository) {
    suspend fun execute(newTrip: NewTrip): CreateTripResponse {
        return tripRepository.createTrip(newTrip);
    }
}











