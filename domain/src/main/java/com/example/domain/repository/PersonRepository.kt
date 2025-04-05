package com.example.domain.repository

import com.example.domain.model.auth.LoginResponse
import com.example.domain.model.person.PersonDetails
import com.example.domain.model.person.RegistryPerson
import com.example.domain.model.person.create.CreatePersonResponse
import com.example.domain.model.person.create.NewPerson


interface PersonRepository {
    suspend fun getPerson(id: Long): PersonDetails;
    suspend fun getPersons(): List<RegistryPerson>;
    suspend fun createPerson(newPerson: NewPerson): CreatePersonResponse;
    suspend fun login(login: String, password: String): LoginResponse;
}
