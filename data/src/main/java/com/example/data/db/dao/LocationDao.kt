package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.db.entity.LocationEntity

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity)

    @Query("SELECT * FROM locations WHERE id=:id LIMIT 1")
    suspend fun getLocationById(id: Long): LocationEntity?

    @Update
    suspend fun updateLocation(location: LocationEntity)

    @Query("SELECT * FROM locations WHERE sent = false")
    suspend fun getUnsentLocations(): List<LocationEntity>

    @Query("UPDATE locations SET sent = true WHERE id IN (:ids)")
    suspend fun markAsSent(ids: List<Long>)
}