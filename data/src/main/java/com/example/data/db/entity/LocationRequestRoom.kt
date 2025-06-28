package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.db.room_util.DateConverter
import java.util.Date

@Entity(tableName = "locations")
@TypeConverters(DateConverter::class)
data class LocationRequestRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    // Координаты широты и долготы сохраняются отдельно
    val latitude: Double,
    val longitude: Double,
    val status: String,
    val fetchDate: Date?,
)