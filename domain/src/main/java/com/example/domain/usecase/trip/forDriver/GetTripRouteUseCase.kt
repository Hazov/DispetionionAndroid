package com.example.domain.usecase.trip.forDriver

import com.example.domain.model.trip.forDriver.tripRoute.TripRoute
import com.example.domain.repository.TripRepository

class GetTripRouteUseCase(private val tripRepository: TripRepository) {

    suspend fun execute(): TripRoute? {
        return tripRepository.getTripRouteForDriver();
    }
}