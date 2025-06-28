package com.example.data.model.trip.gps

import java.util.Date

class GpsDataR(
    val id: Long,
    val longitude: Double,
    val latitude: Double,
    val type: String,
    val fetchDate: Date
)