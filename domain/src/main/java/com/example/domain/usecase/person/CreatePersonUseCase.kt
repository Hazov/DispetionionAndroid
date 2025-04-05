package com.example.domain.usecase.person

import com.example.domain.model.person.create.CreatePersonResponse
import com.example.domain.model.person.create.NewPerson
import com.example.domain.model.truck.create.CreateTruckResponse
import com.example.domain.model.truck.create.NewTruck
import com.example.domain.repository.PersonRepository
import com.example.domain.repository.TruckRepository

class CreatePersonUseCase(private val personRepository: PersonRepository) {
    suspend fun execute(newPerson: NewPerson): CreatePersonResponse {
        return personRepository.createPerson(newPerson);
    }
}