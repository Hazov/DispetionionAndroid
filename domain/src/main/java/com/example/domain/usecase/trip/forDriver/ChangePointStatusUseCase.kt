package com.example.domain.usecase.trip.forDriver

import com.example.domain.model.gps.Location
import com.example.domain.model.trip.forDriver.changeStatus.ChangePointStatusResponse
import com.example.domain.model.trip.forDriver.changeStatus.ChangeTripGpsData
import com.example.domain.model.trip.forDriver.changeStatus.ChangeTripPointStatus
import com.example.domain.repository.TripRepository
import java.util.Date

class ChangePointStatusUseCase(private val tripRepository: TripRepository) {
    suspend fun execute(id: Long, gpsCoordinates: Location): ChangePointStatusResponse {
        val gpsTripData = ChangeTripGpsData(gpsCoordinates.longitude, gpsCoordinates.latitude)
        val changeTripPointStatus = ChangeTripPointStatus(id, gpsTripData)
        return tripRepository.changePointStatus(changeTripPointStatus)
    }
}