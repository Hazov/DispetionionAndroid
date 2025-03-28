package com.example.data.model.person

import com.example.domain.model.person.PersonDetails

data class PersonDetailsResponse(
    val id: Long,
    val lastName: String,
    val firstName: String,
    val middleName: String,
    val email: String,
    val registrationDate: String,
    val company: String,
    val position: String,
    val isFired: Boolean
){
    fun toDomainPersonDetails(): PersonDetails {
        return PersonDetails(id, lastName, firstName, middleName, email, registrationDate, company, position, isFired)
    }
}





