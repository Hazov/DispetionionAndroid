package com.example.data.model.person

import com.example.domain.model.person.RegistryPerson

data class PersonRegistryResponse(val id:Long?, val firstName: String?, val lastName: String?, val position: String?) {
    fun toDomainPersonDetails(): RegistryPerson {
        return RegistryPerson(id, firstName, lastName, position)
    }
}