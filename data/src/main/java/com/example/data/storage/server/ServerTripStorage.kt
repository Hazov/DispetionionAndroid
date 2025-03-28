package com.example.data.storage.server

import com.example.data.model.trip.TripDetailsResponse
import com.example.data.model.trip.TripsRegistryResponse
import com.example.data.storage.TripStorage
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerTripStorage : TripStorage {
    @GET("/api/v1/trip/{id}/details")
    override suspend fun getTrip(@Path("id") id: Long): TripDetailsResponse;

    @GET("/api/v1/trip/all")
    override suspend fun getTrips(): TripsRegistryResponse;
}