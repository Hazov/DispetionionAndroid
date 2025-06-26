package com.example.data.model.trip.forDriver.changeStatus.request

class ChangePointStatusRequest(
    val id: Long,
    val newStatus: String,
    val gpsData: ChangeTripGpsDataReq?
)