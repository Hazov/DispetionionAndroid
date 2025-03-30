package com.example.data.model.trip.details

import com.example.domain.model.trip.details.TripDetails
import java.util.Date


data class TripDetailsR(
    val id: Long,
    val cargos: List<TripDetailsCargoR>,
    val sourceAddress: TripDetailsAddressR,
    val destinationAddress: TripDetailsAddressR,
    val isCompleted: Boolean,
    val truck: TripDetailsTruckR,
    val driver: TripDetailsDriverR,
    val creationDate: Date
) {
    fun toDomainTripDetails(): TripDetails {
        return TripDetails(
            id,
            cargos.map { cargo -> cargo.toDomainTripDetailsCargo() },
            sourceAddress.toDomainTripDetailsAddress(),
            destinationAddress.toDomainTripDetailsAddress(),
            isCompleted,
            truck.toDomainTripDetailsTruck(),
            driver.toDomainTripDetailsDriver(),
            creationDate
        )

    }
}