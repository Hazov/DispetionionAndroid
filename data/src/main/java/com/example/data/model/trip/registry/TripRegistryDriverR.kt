package com.example.data.model.trip.registry

import com.example.domain.model.trip.registry.RegistryTripDriver

data class TripRegistryDriverR(val firstName: String, val lastName: String){
    fun toDomainRegistryTripDriver():RegistryTripDriver{
        return RegistryTripDriver(firstName, lastName)
    }
}