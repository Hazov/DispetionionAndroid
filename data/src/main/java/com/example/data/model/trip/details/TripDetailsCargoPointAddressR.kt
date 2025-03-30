package com.example.data.model.trip.details

import com.example.domain.model.trip.details.TripDetailsCargoPointAddress

data class TripDetailsCargoPointAddressR(
    val city: String,
    val street: String,
    val house: String
){
    fun toDomainTripDetailsCargoPointAddress():TripDetailsCargoPointAddress{
        return TripDetailsCargoPointAddress(city, street, house)
    }
}