package com.example.domain.model.trip.details

import java.util.Date

data class TripDetailsCargoPoint(
    val address: TripDetailsCargoPointAddress,
    val type: String,
    val serialNumber: Int,
    val arrivalForUploadingDate: Date?,
    val arrivalForUnloadingDate: Date?,
    val completionUploadingDate: Date?,
    val completionUnloadingDate: Date?,
    val isCompleted: Boolean
)