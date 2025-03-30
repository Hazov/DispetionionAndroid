package com.example.data.model.truck.registry

import com.example.domain.model.truck.RegistryTruck

data class TruckRegistryResponse(val id: Long, val brand: String, val model: String, val roadNumber: String){
    fun toDomainRegistryTruck(): RegistryTruck{
        return RegistryTruck(id, brand, model, roadNumber)
    }
}