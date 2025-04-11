package com.example.data.model.person.details

import com.example.domain.model.person.details.PersonDetailsCompany

data class PersonDetailsCompanyR(val name: String) {
    fun toDomainPersonDetailsCompany() : PersonDetailsCompany{
        return PersonDetailsCompany(name)
    }
}