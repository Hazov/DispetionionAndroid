package com.example.data.model.trip.details

import com.example.domain.model.trip.details.TripDetailsCargo

data class TripDetailsCargoR(
    val cost: Double,
    val kmDistance: Double,
    val customer: TripDetailsCargoCustomerR,
    val points: List<TripDetailsCargoPointR>
){
    fun toDomainTripDetailsCargo():TripDetailsCargo{
        return TripDetailsCargo(
            cost,
            kmDistance,
            customer.toDomainTripDetailsCargoCustomer(),
            points.map { point -> point.toDomainTripDetailsCargoPoint() })
    }
}
