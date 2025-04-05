package com.example.data.model.person.create

data class CreatePersonRequest(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val email: String
)