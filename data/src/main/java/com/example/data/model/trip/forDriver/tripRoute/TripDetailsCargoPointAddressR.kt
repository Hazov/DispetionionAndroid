package com.example.data.model.trip.forDriver.tripRoute

import com.example.domain.model.trip.forDriver.tripRoute.TripRoutePointAddress

data class TripRoutePointAddressR (
    val city: String,
    val street: String,
    val house: String,
) {
    fun toDomainTripRoutePointAddress(): TripRoutePointAddress{
        return TripRoutePointAddress(city, street, house)
    }
}