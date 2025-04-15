package com.example.data.model.trip.details

import com.example.domain.model.trip.details.TripDetailsCargo

data class TripDetailsCargoR(
    val customer: TripDetailsCargoCustomerR,
    val points: List<TripDetailsCargoPointR>,
    val name: String
){
    fun toDomainTripDetailsCargo():TripDetailsCargo{
        return TripDetailsCargo(
            customer.toDomainTripDetailsCargoCustomer(),
            points.map { point -> point.toDomainTripDetailsCargoPoint() },
            name)
    }
}
