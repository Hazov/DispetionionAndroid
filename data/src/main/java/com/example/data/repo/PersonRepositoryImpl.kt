package com.example.data.repo

import com.example.data.storage.server.ServerPersonStorage
import com.example.domain.model.person.PersonDetails
import com.example.domain.model.person.RegistryPerson
import com.example.domain.repository.PersonRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonRepositoryImpl: PersonRepository {
    override suspend fun GetPerson(id: Long): PersonDetails {
        val retro = Retrofit.Builder().baseUrl("http://192.168.56.1:8080").addConverterFactory(GsonConverterFactory.create()).build()
        val client = retro.create(ServerPersonStorage::class.java)
        return client.getPerson(id).toDomainPersonDetails()
    }

    override suspend fun GetPersons(): List<RegistryPerson> {
        val retro = Retrofit.Builder().baseUrl("http://192.168.56.1:8080").addConverterFactory(GsonConverterFactory.create()).build()
        val client = retro.create(ServerPersonStorage::class.java)
        return client.getPersons().users.map { person -> person.toDomainPersonDetails() }
    }
}