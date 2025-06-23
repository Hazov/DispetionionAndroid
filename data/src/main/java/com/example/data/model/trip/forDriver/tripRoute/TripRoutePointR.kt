package com.example.data.model.trip.forDriver.tripRoute

import com.example.domain.model.trip.forDriver.tripRoute.TripRoutePoint
import java.util.Date

data class TripRoutePointR(
    val id: Long,
    val address: TripRoutePointAddressR,
    val type: String,
    val serialNumber: Int,
    val arrivalDate: Date?,
    val completionDate: Date?,
    val isCompleted: Boolean,
    val cargoName: String
) {
    fun toDomainTripRoutePoint():TripRoutePoint{
        return TripRoutePoint(
            id,
            address.toDomainTripRoutePointAddress(),
            type,
            serialNumber,
            arrivalDate,
            completionDate,
            isCompleted,
            cargoName
        )
    }
}