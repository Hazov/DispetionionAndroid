package com.example.data.model.truck.create

import com.example.domain.model.truck.create.CreateTruckResponse

data class CreateTruckResponse(
    val brand: String,
    val model: String,
    val roadNumber: String
){
    fun toDomainCreateTruckResponse() : CreateTruckResponse {
        return CreateTruckResponse(brand, model, roadNumber)
    }
}