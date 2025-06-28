package com.example.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.db.entity.LocationRequestRoom


@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertLocation(location: LocationRequestRoom)

    @Update
     fun updateLocation(location: LocationRequestRoom)

    @Query("SELECT * FROM locations")
     fun getUnsentLocations(): LiveData<List<LocationRequestRoom>>

    @Query("SELECT * FROM locations WHERE id = :id LIMIT 1")
     fun getLocationById(id: Long): LocationRequestRoom?
}