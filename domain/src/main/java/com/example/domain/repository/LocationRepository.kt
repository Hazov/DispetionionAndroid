package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.model.gps.Location


interface LocationRepository {
    suspend fun addLocation(latitude: Double, longitude: Double, status: String)
    suspend fun sendLocation(location: Location)
    suspend fun defineLocation(): Location?
    suspend fun getUnsentLocations(): List<Location>
}