package com.example.data.repo

import com.example.data.model.person.registry.PersonRegistryR
import com.example.data.storage.PersonStorage
import com.example.domain.model.person.PersonDetails
import com.example.domain.model.person.RegistryPerson
import com.example.domain.repository.PersonRepository

class PersonRepositoryImpl(private val personStorage: PersonStorage): PersonRepository {
    override suspend fun GetPerson(id: Long): PersonDetails {
        return personStorage.getPerson(id).toDomainPersonDetails()
    }

    override suspend fun GetPersons(): List<RegistryPerson> {
        return personStorage.getPersons().users.map { person -> person.toDomainPersonDetails() }
    }
}