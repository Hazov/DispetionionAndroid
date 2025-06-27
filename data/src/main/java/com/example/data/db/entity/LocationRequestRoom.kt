package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationRequestRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    // Координаты широты и долготы сохраняются отдельно
    val latitude: Double,
    val longitude: Double
)