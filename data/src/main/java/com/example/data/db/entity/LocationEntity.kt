package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val latitude: Double,
    val longitude: Double,
    val status: String,
    val sent: Boolean = false,
    val fetchDate: Date
)