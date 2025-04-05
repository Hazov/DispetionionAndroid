package com.example.data.model.person.create

import com.example.domain.model.person.create.CreatePersonResponse

data class CreatePersonResponse(
    val brand: String,
    val model: String,
    val roadNumber: String
){
    fun toDomainCreatePersonResponse() : CreatePersonResponse {
        return CreatePersonResponse(brand, model, roadNumber)
    }
}