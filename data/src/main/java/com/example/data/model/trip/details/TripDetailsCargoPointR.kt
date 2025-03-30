package com.example.data.model.trip.details

import com.example.domain.model.trip.details.TripDetailsCargoPoint
import java.util.Date

data class TripDetailsCargoPointR(
    val address: TripDetailsCargoPointAddressR,
    val type: String,
    val serialNumber: Int,
    val arrivalForUploadingDate: Date,
    val arrivalForUnloadingDate: Date,
    val completionUploadingDate: Date,
    val completionUnloadingDate: Date,
    val isCompleted: Boolean
){
    fun toDomainTripDetailsCargoPoint():TripDetailsCargoPoint{
        return TripDetailsCargoPoint(
            address.toDomainTripDetailsCargoPointAddress(),
            type,
            serialNumber,
            arrivalForUploadingDate,
            arrivalForUnloadingDate,
            completionUploadingDate,
            completionUnloadingDate,
            isCompleted)
    }
}