package com.example.domain.model.trip.registry

import java.util.Date

data class RegistryTrip(val id: Long,
                        val isCompleted: Boolean?,
                        val cargosCount: Int?,
                        val creationDate: Date?,
                        val sourceAddress: RegistryTripAddress?,
                        val destinationAddress: RegistryTripAddress?,
                        val truck: RegistryTripTruck?,
                        val driver: RegistryTripDriver?
){}