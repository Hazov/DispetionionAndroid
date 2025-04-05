package com.example.data.model.trip.forDriver.tripRoute

import com.example.domain.model.trip.forDriver.tripRoute.TripRoute
import com.example.domain.model.trip.forDriver.tripRoute.TripRoutePoint


class TripRouteResponse(val success: Boolean, val points: List<TripRoutePointR>) {
    fun toDomainTripRouteResponse() : TripRoute{
        return TripRoute(
            points.map { point -> point.toDomainTripRoutePoint()}.toList()
        )
    }
}