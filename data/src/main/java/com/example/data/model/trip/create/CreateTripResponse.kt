package com.example.data.model.trip.create

import com.example.domain.model.trip.create.CreateTripResponse

data class CreateTripResponse(
    val brand: String,
    val model: String,
    val roadNumber: String
){
    fun toDomainCreateTripResponse() : CreateTripResponse {
        return CreateTripResponse(brand, model, roadNumber)
    }
}