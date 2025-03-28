package com.example.data.model.trip

import com.example.domain.model.trip.RegistryTrip

data class TripRegistryResponse(val id: Long) {
    fun toDomainTripDetails(): RegistryTrip{
        return RegistryTrip(id)
    }
}