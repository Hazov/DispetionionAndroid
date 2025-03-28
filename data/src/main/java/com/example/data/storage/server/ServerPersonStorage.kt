package com.example.data.storage.server

import com.example.data.model.person.PersonDetailsResponse
import com.example.data.model.person.PersonsRegistryResponse
import com.example.data.storage.PersonStorage
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerPersonStorage : PersonStorage {

    @GET("/api/v1/user/{id}/details")
    override suspend fun getPerson(@Path("id") id: Long): PersonDetailsResponse

    @GET("/api/v1/user/all")
    override suspend fun getPersons(): PersonsRegistryResponse

}