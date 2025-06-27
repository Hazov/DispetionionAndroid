package com.example.data.db.entity


import androidx.room.*

@Entity(tableName = "change_status_requests")
data class ChangeStatusRequestRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val userId: Long,
    val pointId: Long,

    @Embedded(prefix = "gps_")
    var location: LocationRequestRoom?,

    val newStatus: String
)