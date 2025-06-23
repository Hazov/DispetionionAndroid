package com.example.domain.model.trip.forDriver.tripRoute

import com.example.domain.model.trip.details.TripDetailsCargoPointAddress
import java.util.Date

data class TripRoutePoint(
    val id: Long,
    val address: TripRoutePointAddress,
    val type: String,
    val serialNumber: Int,
    var arrivalDate: Date?,
    var completionDate: Date?,
    var isCompleted: Boolean,
    val cargoName: String
)