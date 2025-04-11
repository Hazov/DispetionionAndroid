package com.example.data.model.trip.create

import com.example.domain.model.trip.create.NewCargo

data class CreateTripRequest(
    val cargos: List<NewCargo>,
    val truckId: Long,
    val driverId: Long,



)