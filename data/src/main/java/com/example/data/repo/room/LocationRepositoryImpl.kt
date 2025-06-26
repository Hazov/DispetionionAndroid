package com.example.data.repo.room

import android.annotation.SuppressLint
import android.content.Context
import com.example.data.db.dao.LocationDao
import com.example.data.db.entity.LocationEntity
import com.example.domain.model.gps.Location
import com.example.domain.repository.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val applicationContext: Context,
    private val locationDao: LocationDao
) : LocationRepository {

    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(applicationContext)

    override suspend fun addLocation(latitude: Double, longitude: Double, status: String) {
        val locationEntity = LocationEntity(
            id = null,
            latitude = latitude,
            longitude = longitude,
            status = status,
            fetchDate = Date()
        )
        locationDao.insertLocation(locationEntity)
    }

    override suspend fun markAsSent(id: Long) {
        val existing = locationDao.getLocationById(id)
        if (existing != null) {
            // Обновляем запись, установив флаг sent=true
            locationDao.updateLocation(existing.copy(sent = true))
        }
    }

    override suspend fun getUnsentLocations(): List<Location> {
        // Берём из базы данных список LocationEntity
        val entities = locationDao.getUnsentLocations()

        // Преобразуем каждую сущность в доменную модель Location
        return entities.map { entity ->
            Location(
                id = entity.id,
                latitude = entity.latitude,
                longitude = entity.longitude,
                status = entity.status,
                fetchDate = entity.fetchDate,
                sent = entity.sent
            )
        }
    }

    override suspend fun sendLocation(location: Location){
        val locationEntity = LocationEntity(null, location.latitude, location.longitude, location.status, location.sent, location.fetchDate)
        locationDao.insertLocation(locationEntity)

    }

    @SuppressLint("MissingPermission")
    override fun defineLocation(): android.location.Location? {
        // Получаем текущие координаты
        return fusedLocationClient.lastLocation.await()
    }
}