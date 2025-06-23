package com.example.domain.model.gps

import java.util.Date

data class Location(
    val id: Long?,
    val latitude: Double,
    val longitude: Double,
    val status: String,
    val fetchDate: Date,
    val sent: Boolean
)