package com.example.data.model.trip

import com.example.domain.model.trip.TripDetails

data class TripDetailsResponse(val id:Long) {
    fun toDomainTripDetails(): TripDetails{
        return TripDetails(id)
    }
}