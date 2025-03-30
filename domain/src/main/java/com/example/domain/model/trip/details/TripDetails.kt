package com.example.domain.model.trip.details

import java.util.Date


data class TripDetails(
    val id: Long,
    val cargos: List<TripDetailsCargo>,
    val sourceAddress: TripDetailsAddress,
    val destinationAddress: TripDetailsAddress,
    val isCompleted: Boolean,
    val truck: TripDetailsTruck,
    val driver: TripDetailsDriver,
    val creationDate: Date
)