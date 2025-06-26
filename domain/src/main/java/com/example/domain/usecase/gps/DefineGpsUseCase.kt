package com.example.domain.usecase.gps

import com.example.domain.model.gps.Location
import com.example.domain.repository.LocationRepository


class DefineGpsUseCase constructor(private val locationRepository: LocationRepository) {
     fun execute(): android.location.Location? {
        return locationRepository.defineLocation();
    }


}