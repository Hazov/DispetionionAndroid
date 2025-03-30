package com.example.data.model.person.registry

import com.example.domain.model.person.RegistryPerson

data class PersonRegistryR(val id:Long?, val firstName: String?, val lastName: String?, val position: String?) {
    fun toDomainPersonDetails(): RegistryPerson {
        return RegistryPerson(id, firstName, lastName, position)
    }
}