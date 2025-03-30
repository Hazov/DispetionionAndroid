package com.example.data.model.person.details

import com.example.domain.model.person.PersonDetails

data class PersonDetailsResponse(
    val id: Long,
    val lastName: String,
    val firstName: String,
    val middleName: String,
    val email: String,
    val registrationDate: String,
    val company: PersonDetailsCompanyR,
    val position: String,
    val isFired: Boolean
){
    fun toDomainPersonDetails(): PersonDetails {
        var domainCompany = company.toDomainPersonDetailsCompany()
        return PersonDetails(id, lastName, firstName, middleName, email, registrationDate, domainCompany, position, isFired)
    }
}





