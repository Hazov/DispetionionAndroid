package com.example.domain.repository

import com.example.domain.model.person.PersonDetails
import com.example.domain.model.person.RegistryPerson


interface PersonRepository {
    suspend fun GetPerson(id: Long): PersonDetails;
    suspend fun GetPersons(): List<RegistryPerson>;
}
