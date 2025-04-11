package com.example.domain.repository

import com.example.domain.model.auth.LoginResponse
import com.example.domain.model.person.autocomplete.DriverForAutoComplete
import com.example.domain.model.person.details.PersonDetails
import com.example.domain.model.person.registry.RegistryPerson
import com.example.domain.model.person.create.CreatePersonResponse
import com.example.domain.model.person.create.NewPerson


interface PersonRepository {
    suspend fun getPerson(id: Long): PersonDetails
    suspend fun getPersons(): List<RegistryPerson>
    suspend fun createPerson(newPerson: NewPerson): CreatePersonResponse
    suspend fun login(login: String, password: String): LoginResponse
    suspend fun getDriversForAutoComplete(): List<DriverForAutoComplete>
}
