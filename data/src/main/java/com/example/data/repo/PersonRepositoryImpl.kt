package com.example.data.repo

import com.example.data.auth.TokenManager
import com.example.data.model.person.create.CreatePersonRequest
import com.example.data.storage.PersonStorage
import com.example.domain.model.auth.login.LoginRequest
import com.example.domain.model.auth.login.LoginResponse
import com.example.domain.model.auth.permissions.PermissionsResponse
import com.example.domain.model.person.autocomplete.DriverForAutoComplete
import com.example.domain.model.person.details.PersonDetails
import com.example.domain.model.person.registry.RegistryPerson
import com.example.domain.model.person.create.CreatePersonResponse
import com.example.domain.model.person.create.NewPerson
import com.example.domain.repository.PersonRepository

class PersonRepositoryImpl(private val personStorage: PersonStorage) : PersonRepository {
    override suspend fun getPerson(id: Long): PersonDetails {
        return personStorage.getPerson(id).toDomainPersonDetails()
    }

    override suspend fun getPersons(): List<RegistryPerson> {
        return personStorage.getPersons().users.map { person -> person.toDomainPersonDetails() }
    }

    override suspend fun createPerson(newPerson: NewPerson): CreatePersonResponse {
        val req = CreatePersonRequest(
            newPerson.firstName,
            newPerson.lastName,
            newPerson.middleName,
            newPerson.email
        )
        val dataResponse = personStorage.createPerson(req)
        return dataResponse.toDomainCreatePersonResponse()
    }

    override suspend fun getDriversForAutoComplete(): List<DriverForAutoComplete>{
        val body = personStorage.getDriversForAutoComplete()
        return body.drivers.map { driver -> driver.toDomainDriver() }
    }
}