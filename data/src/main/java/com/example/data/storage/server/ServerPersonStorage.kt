package com.example.data.storage.server

import com.example.data.model.person.create.CreatePersonRequest
import com.example.data.model.person.create.CreatePersonResponse
import com.example.data.model.person.details.PersonDetailsResponse
import com.example.data.model.person.registry.PersonsRegistryResponse
import com.example.data.storage.PersonStorage
import com.example.domain.model.auth.LoginRequest
import com.example.domain.model.auth.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServerPersonStorage : PersonStorage {

    @GET("/api/v1/user/{id}/details")
    override suspend fun getPerson(@Path("id") id: Long): PersonDetailsResponse

    @GET("/api/v1/user/all")
    override suspend fun getPersons(): PersonsRegistryResponse

    @POST("/api/v1/user/create")
    override suspend fun createPerson(@Body request:CreatePersonRequest): CreatePersonResponse;

    @POST("/api/v1/auth/login")
    override suspend fun login(@Body request: LoginRequest): LoginResponse

}