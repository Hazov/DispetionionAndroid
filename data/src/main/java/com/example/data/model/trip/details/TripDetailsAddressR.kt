package com.example.data.model.trip.details

import com.example.domain.model.trip.details.TripDetailsAddress

data class TripDetailsAddressR(
    val city: String,
    val street: String,
    val house: String
){
    fun toDomainTripDetailsAddress():TripDetailsAddress{
        return TripDetailsAddress(city,street,house)
    }
}