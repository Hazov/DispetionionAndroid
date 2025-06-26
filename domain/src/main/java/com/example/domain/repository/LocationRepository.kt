package com.example.domain.repository

import com.example.domain.model.gps.Location


interface LocationRepository {
    suspend fun addLocation(latitude: Double, longitude: Double, status: String)
    suspend fun markAsSent(id: Long)
    suspend fun getUnsentLocations(): List<Location>
    suspend fun sendLocation(location: Location)
    fun defineLocation(): android.location.Location?
}