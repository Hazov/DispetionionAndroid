package com.example.domain.model.person

data class PersonDetails(
    val id:Long?,
    val lastName: String?,
    val firstName: String?,
    val middleName: String?,
    val email: String?,
    val registrationDate: String?,
    val company: String?,
    val position: String?,
    val isFired: Boolean?

)