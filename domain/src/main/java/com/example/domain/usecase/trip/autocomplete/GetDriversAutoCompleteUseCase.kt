package com.example.domain.usecase.trip.autocomplete

import com.example.domain.model.person.autocomplete.DriverForAutoComplete
import com.example.domain.repository.PersonRepository

class GetDriversAutoCompleteUseCase(private val personRepository: PersonRepository) {
    suspend fun execute(): List<DriverForAutoComplete> {
        return personRepository.getDriversForAutoComplete();
    }
}