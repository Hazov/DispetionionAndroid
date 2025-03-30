package com.example.data.storage

import com.example.data.model.person.details.PersonDetailsResponse
import com.example.data.model.person.registry.PersonsRegistryResponse


interface PersonStorage {
    suspend fun getPerson(id: Long) : PersonDetailsResponse;
    suspend fun getPersons(): PersonsRegistryResponse;
}