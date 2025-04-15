package com.example.domain.model.trip.details

data class TripDetailsCargo(
    val customer: TripDetailsCargoCustomer,
    var points: List<TripDetailsCargoPoint>,
    val name: String
)
