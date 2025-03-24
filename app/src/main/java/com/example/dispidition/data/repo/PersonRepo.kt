package com.example.dispidition.data.repo

import retrofit2.http.GET

interface PersonRepo {
    @GET
    suspend fun getPerson();
}