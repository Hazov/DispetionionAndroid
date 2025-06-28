package com.example.data.repo.room

import android.annotation.SuppressLint
import android.content.Context
import com.example.data.db.dao.LocationDao
import com.example.data.db.entity.LocationRequestRoom
import com.example.domain.model.gps.Location
import com.example.domain.repository.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val applicationContext: Context,
    private val locationDao: LocationDao
) : LocationRepository {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(applicationContext)

    override suspend fun addLocation(latitude: Double, longitude: Double, status: String) {
        val locationEntity = LocationRequestRoom(
            id = null,
            latitude = latitude,
            longitude = longitude,
            status = status,
            Date()
        )
        locationDao.insertLocation(locationEntity)
    }


    override suspend fun sendLocation(location: Location) {
        val locationEntity = LocationRequestRoom(
            null,
            location.latitude,
            location.longitude,
            location.status,
            location.fetchDate
        )
        locationDao.insertLocation(locationEntity)

    }

    @SuppressLint("MissingPermission")
    override suspend fun defineLocation(): Location? {
        // Получаем текущие координаты
        val androidLocation = fusedLocationClient.lastLocation.await()
        if (androidLocation == null) {
            return null;
        }
        return Location(null, androidLocation.latitude, androidLocation.longitude, "", Date())

    }

    override suspend fun getUnsentLocations(): List<Location> {

        return locationDao.getUnsentLocations().value!!
            .map { item ->
                Location(
                        item.id as Long?,
                        item.latitude,
                        item.longitude,
                        item.status,
                        item.fetchDate
                    )
                }
            }

}