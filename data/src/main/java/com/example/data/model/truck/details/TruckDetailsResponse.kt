package com.example.data.model.truck.details

import com.example.domain.model.truck.details.TruckDetails
import com.example.domain.model.truck.details.TruckDetailsTrip

data class TruckDetailsResponse(
    val brand: String,
    val model: String,
    val roadNumber: String,
    val ownerName: String,
    val trip: TruckDetailsTrip
) {
    fun toDomainTruckDetails(): TruckDetails{
        return TruckDetails(brand, model, roadNumber, ownerName, trip)
    }
}