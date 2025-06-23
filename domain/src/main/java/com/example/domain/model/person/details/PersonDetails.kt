package com.example.domain.model.person.details

import java.util.Date

data class PersonDetails(
    val id:Long?,
    val lastName: String?,
    val firstName: String?,
    val middleName: String?,
    val email: String?,
    val registrationDate: Date,
    val company: PersonDetailsCompany?,
    val position: String?,
    val isFired: Boolean?

)