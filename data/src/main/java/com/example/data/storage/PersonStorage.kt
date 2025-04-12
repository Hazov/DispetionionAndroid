package com.example.data.storage

import com.example.data.model.person.autocomplete.DriversForAutoCompleteResponse
import com.example.data.model.person.create.CreatePersonRequest
import com.example.data.model.person.create.CreatePersonResponse
import com.example.data.model.person.details.PersonDetailsResponse
import com.example.data.model.person.registry.PersonsRegistryResponse


interface PersonStorage {
    suspend fun getPerson(id: Long) : PersonDetailsResponse;
    suspend fun getPersons(): PersonsRegistryResponse
    suspend fun createPerson(request:CreatePersonRequest): CreatePersonResponse
    suspend fun getDriversForAutoComplete(): DriversForAutoCompleteResponse

}