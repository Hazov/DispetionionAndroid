package com.example.data.model.trip.registry

import com.example.domain.model.trip.registry.RegistryTrip
import java.time.LocalDate
import java.util.Date


data class TripRegistryR(
    val id: Long,
    val isCompleted: Boolean,
    val cargosCount: Int,
    val creationDate: Date,
    val sourceAddress: TripRegistryAddressR,
    val destinationAddress: TripRegistryAddressR,
    val truck: TripRegistryTruckR,
    val driver: TripRegistryDriverR

) {
    fun toDomainTripDetails(): RegistryTrip {
        return RegistryTrip(
            id,
            isCompleted,
            cargosCount,
            creationDate,
            sourceAddress.toDomainRegistryTripAddress(),
            destinationAddress.toDomainRegistryTripAddress(),
            truck.toDomainRegistryTripTruck(),
            driver.toDomainRegistryTripDriver()
            )
    }
}