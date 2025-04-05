package com.example.data.model.trip.forDriver.tripRoute

import com.example.domain.model.trip.forDriver.tripRoute.TripRoutePoint
import java.util.Date

data class TripRoutePointR(
    val address: TripRoutePointAddressR,
    val type: String,
    val serialNumber: Int,
    val arrivalForUploadingDate: Date?,
    val arrivalForUnloadingDate: Date?,
    val completionUploadingDate: Date?,
    val completionUnloadingDate: Date?,
    val isCompleted: Boolean,
    val cargoName: String
) {
    fun toDomainTripRoutePoint():TripRoutePoint{
        return TripRoutePoint(
            address.toDomainTripRoutePointAddress(),
            type,
            serialNumber,
            arrivalForUploadingDate,
            arrivalForUnloadingDate,
            completionUploadingDate,
            completionUnloadingDate,
            isCompleted,
            cargoName
        )
    }
}