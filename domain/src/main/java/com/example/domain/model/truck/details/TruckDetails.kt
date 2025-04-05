package com.example.domain.model.truck.details

data class TruckDetails(
    val brand: String?,
    val model: String?,
    val roadNumber: String?,
    val ownerName: String?,
    val trip: TruckDetailsTrip?
)