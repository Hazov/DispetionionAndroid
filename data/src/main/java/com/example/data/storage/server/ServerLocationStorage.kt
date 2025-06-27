package com.example.data.storage.server

import com.example.data.storage.LocationStorage
import com.example.domain.model.gps.Location
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerLocationStorage : LocationStorage{
    @POST("/api/v1/location/send")
    override suspend fun uploadLocation(@Body location: Location)
}