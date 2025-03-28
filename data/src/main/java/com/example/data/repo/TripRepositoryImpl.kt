package com.example.data.repo

import com.example.data.storage.server.ServerTripStorage
import com.example.domain.model.trip.RegistryTrip
import com.example.domain.model.trip.TripDetails
import com.example.domain.repository.TripRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TripRepositoryImpl : TripRepository {
    override suspend fun GetTrip(id: Long): TripDetails {
        val retro = Retrofit.Builder().baseUrl("http://192.168.56.1:8080").addConverterFactory(GsonConverterFactory.create()).build()
        val client = retro.create(ServerTripStorage::class.java)
        return client.getTrip(id).toDomainTripDetails()
    }

    override suspend fun GetTrips(): List<RegistryTrip> {
        val retro = Retrofit.Builder().baseUrl("http://192.168.56.1:8080").addConverterFactory(GsonConverterFactory.create()).build()
        val client = retro.create(ServerTripStorage::class.java)
        return client.getTrips().trips.map { trip -> trip.toDomainTripDetails() }
    }
}