package com.example.domain.usecase.trip.get_trip_gps

import com.example.domain.repository.TripRepository

class GetTripGpsUseCase(private val tripRepository: TripRepository) {
    suspend fun execute(tripId:Long){
        tripRepository.getTripGpsData(tripId);
    }
}