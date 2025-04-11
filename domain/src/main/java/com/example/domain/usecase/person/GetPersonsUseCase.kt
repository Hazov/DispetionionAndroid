package com.example.domain.usecase.person

import com.example.domain.model.person.registry.RegistryPerson
import com.example.domain.repository.PersonRepository

class GetPersonsUseCase(private val personRepository: PersonRepository) {
    suspend fun execute(): List<RegistryPerson> {
        return personRepository.getPersons()
    }
}