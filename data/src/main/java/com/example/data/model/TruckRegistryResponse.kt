package com.example.data.model

import com.example.domain.model.truck.RegistryTruck

data class TruckRegistryResponse(val brand: String, val model: String, val roadNumber: String){
    fun toDomainRegistryTruck(): RegistryTruck{
        return RegistryTruck(brand = brand, model = model, roadNumber = roadNumber)
    }
}