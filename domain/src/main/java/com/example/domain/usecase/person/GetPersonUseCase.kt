package com.example.domain.usecase.person

import com.example.domain.model.person.PersonDetails
import com.example.domain.repository.PersonRepository

class GetPersonUseCase(private val personRepository: PersonRepository) {
    suspend fun execute(id: Long) : PersonDetails {
        return personRepository.GetPerson(id);
    }
}