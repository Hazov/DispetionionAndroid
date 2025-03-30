package com.example.data.model.trip.details

import com.example.domain.model.trip.details.TripDetailsTruck

data class TripDetailsTruckR(
    val brand: String,
    val model: String,
    val roadNumber: String
){
    fun toDomainTripDetailsTruck():TripDetailsTruck{
        return TripDetailsTruck(brand, model, roadNumber)
    }
}