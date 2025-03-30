package com.example.data.model.trip.details

import com.example.domain.model.trip.details.TripDetailsDriver

data class TripDetailsDriverR(
    val firstName: String,
    val lastName: String
){
    fun toDomainTripDetailsDriver(): TripDetailsDriver{
        return TripDetailsDriver(firstName, lastName)
    }
}