package com.example.domain.usecase.trip.forDriver


import com.example.domain.repository.LocationRepository
import com.example.domain.repository.TripRepository

class ChangeTripStatusUseCase(
    private val tripRepository: TripRepository,
    private val locationRepository: LocationRepository
) {
    suspend fun execute(id: Long, newStatus: String): Boolean {
        val gpsCoordinates = locationRepository.defineLocation()
        try {
            tripRepository.changePointStatus(id, gpsCoordinates, newStatus)
            return true;
        } catch (serverException: Exception) {
            return false;
        }


    }
}