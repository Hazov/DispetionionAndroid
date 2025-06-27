package com.example.data.storage.server

import com.example.data.model.trip.create.CreateTripRequest
import com.example.data.model.trip.create.CreateTripResponse
import com.example.data.model.trip.details.TripDetailsResponse
import com.example.data.model.trip.forDriver.changeStatus.request.ChangeStatusRequest
import com.example.data.model.trip.forDriver.changeStatus.response.ChangePointStatusResponse
import com.example.data.model.trip.forDriver.tripRoute.TripRouteResponse
import com.example.data.model.trip.gps.TripGpsDataResponse
import com.example.data.model.trip.registry.TripsRegistryResponse
import com.example.data.storage.TripStorage
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServerTripStorage : TripStorage {
    @GET("/api/v1/trip/{id}/details")
    override suspend fun getTrip(@Path("id") id: Long): TripDetailsResponse

    @GET("/api/v1/trip/all")
    override suspend fun getTrips(): TripsRegistryResponse

    @POST("/api/v1/trip/create")
    override suspend fun createTrip(request: CreateTripRequest): CreateTripResponse

    @GET("/api/v1/driver/trip/route")
    override suspend fun getTripRouteForDriver(): TripRouteResponse

    @PUT("/api/v1/driver/trip/change_point_status/{pointId}")
    override suspend fun changePointStatus(@Path("pointId") pointId: Long, @Body changeStatusRequest: ChangeStatusRequest): ChangePointStatusResponse;

    @GET("/api/v1/trip/{id}/gps")
    override suspend fun getTripGpsData(@Path("id") id : Long): TripGpsDataResponse
}