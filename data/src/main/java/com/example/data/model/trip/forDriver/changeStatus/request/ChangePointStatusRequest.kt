package com.example.data.model.trip.forDriver.changeStatus.request

class ChangePointStatusRequest(
    val newStatus: String,
    val gpsData: ChangeTripGpsDataReq
)