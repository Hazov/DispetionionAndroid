package com.example.domain.usecase.trip

import com.example.domain.model.trip.create.CreateTripResponse
import com.example.domain.model.trip.create.NewTrip
import com.example.domain.model.truck.create.CreateTruckResponse
import com.example.domain.model.truck.create.NewTruck
import com.example.domain.repository.TripRepository
import com.example.domain.repository.TruckRepository

class CreateTripUseCase(private val tripRepository: TripRepository) {
    suspend fun execute(newTrip: NewTrip): CreateTripResponse {
        return tripRepository.createTrip(newTrip);
    }
}