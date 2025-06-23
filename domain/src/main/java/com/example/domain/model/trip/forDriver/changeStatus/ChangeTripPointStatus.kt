package com.example.domain.model.trip.forDriver.changeStatus

import java.util.Date

class ChangeTripPointStatus(
    val pointId: Long,
    val newStatus: String,
    val gpsData: ChangeTripGpsData
)