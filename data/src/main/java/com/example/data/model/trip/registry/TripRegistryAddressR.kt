package com.example.data.model.trip.registry

import com.example.domain.model.trip.registry.RegistryTripAddress

data class TripRegistryAddressR(val city: String){
    fun toDomainRegistryTripAddress():RegistryTripAddress{
        return RegistryTripAddress(city)
    }
}
