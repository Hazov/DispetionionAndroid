package com.example.data.model.trip.details

import com.example.domain.model.trip.details.TripDetailsCargoPoint
import java.util.Date

data class TripDetailsCargoPointR(
    val address: TripDetailsCargoPointAddressR,
    val type: String,
    val serialNumber: Int,
    val arrivalDate: Date,
    val completionDate: Date,
    val isCompleted: Boolean
){
    fun toDomainTripDetailsCargoPoint():TripDetailsCargoPoint{
        return TripDetailsCargoPoint(
            address.toDomainTripDetailsCargoPointAddress(),
            type,
            serialNumber,
            arrivalDate,
            completionDate,
            isCompleted)
    }
}