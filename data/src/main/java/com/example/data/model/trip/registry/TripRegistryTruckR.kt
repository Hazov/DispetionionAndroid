package com.example.data.model.trip.registry

import com.example.domain.model.trip.registry.RegistryTripTruck

data class TripRegistryTruckR(val roadNumber: String){

    fun toDomainRegistryTripTruck():RegistryTripTruck{
        return RegistryTripTruck(roadNumber)
    }
}