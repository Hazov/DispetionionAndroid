package com.example.domain.model.trip.details

data class TripDetailsCargo(
    val cost: Double,
    val kmDistance: Double,
    val customer: TripDetailsCargoCustomer,
    val points: List<TripDetailsCargoPoint>
)
