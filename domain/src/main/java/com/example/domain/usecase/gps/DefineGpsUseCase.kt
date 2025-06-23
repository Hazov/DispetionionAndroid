package com.example.domain.usecase.gps

import com.example.domain.model.gps.Location
import com.example.domain.repository.LocationRepository


class DefineGpsUseCase constructor(private val locationRepository: LocationRepository) {
    suspend fun execute(status: String): Location {
        val location = locationRepository.defineLocation();

        if (location != null) {
            locationRepository.addLocation(location.latitude, location.longitude, status)
        }
    }


}