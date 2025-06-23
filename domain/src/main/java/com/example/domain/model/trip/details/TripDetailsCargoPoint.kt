package com.example.domain.model.trip.details

import java.util.Date

data class TripDetailsCargoPoint(
    val address: TripDetailsCargoPointAddress,
    val type: String,
    val serialNumber: Int,
    val arrivalDate: Date?,
    val completionDate: Date?,
    val isCompleted: Boolean
)