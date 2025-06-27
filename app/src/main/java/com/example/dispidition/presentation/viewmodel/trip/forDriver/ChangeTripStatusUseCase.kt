package com.example.dispidition.presentation.viewmodel.trip.forDriver

import android.location.Location
import com.example.domain.repository.LocationRepository
import com.example.domain.repository.TripRepository

class ChangeTripStatusUseCase(
    private val tripRepository: TripRepository,
    locationRepository: LocationRepository
) {
    suspend fun execute(id: Long, gpsCoordinates: Location?, newStatus: String): Boolean {
        try {
            tripRepository.changePointStatus(id, gpsCoordinates, newStatus)
            return true;
        } catch (serverException: Exception) {
            return false;
        }


    }
}