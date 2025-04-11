package com.example.domain.model.trip.create

data class NewTrip(
    val cargos: List<NewCargo>,
    val truckId: Long,
    val driverId: Long,
)