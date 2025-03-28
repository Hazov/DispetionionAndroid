package com.example.data.storage

import com.example.data.model.person.PersonDetailsResponse
import com.example.data.model.person.PersonsRegistryResponse


interface PersonStorage {
    suspend fun getPerson(id: Long) : PersonDetailsResponse;
    suspend fun getPersons(): PersonsRegistryResponse;
}