package com.example.data.storage.server

import com.example.data.model.truck.details.TruckDetailsResponse
import com.example.data.model.truck.registry.TrucksRegistryResponse
import com.example.data.storage.TruckStorage
import retrofit2.http.GET
import retrofit2.http.Path


interface ServerTruckStorage: TruckStorage {
    @GET("/api/v1/truck/{id}/details")
    override suspend fun getTruck(@Path("id") id: Long): TruckDetailsResponse;

    @GET("/api/v1/truck/all")
    override suspend fun getTrucks(): TrucksRegistryResponse;
}