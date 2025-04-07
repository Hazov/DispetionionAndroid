package com.example.data.repo


import com.example.data.model.trip.create.CreateTripRequest
import com.example.data.model.trip.forDriver.changeStatus.ChangePointStatusRequest
import com.example.data.storage.TripStorage
import com.example.domain.model.trip.create.CreateTripResponse
import com.example.domain.model.trip.create.NewTrip
import com.example.domain.model.trip.details.TripDetails
import com.example.domain.model.trip.forDriver.changeStatus.ChangePointStatusResponse
import com.example.domain.model.trip.forDriver.tripRoute.TripRoute
import com.example.domain.model.trip.registry.RegistryTrip
import com.example.domain.repository.TripRepository

class TripRepositoryImpl(private val tripStorage: TripStorage) : TripRepository {
    override suspend fun getTrip(id: Long): TripDetails {
        return tripStorage.getTrip(id).tripDetails.toDomainTripDetails()
    }

    override suspend fun getTrips(): List<RegistryTrip> {
        return tripStorage.getTrips().trips.map { trip -> trip.toDomainTripDetails() }
    }

    override suspend fun createTrip(newTrip: NewTrip): CreateTripResponse{
        var dataResponse = tripStorage.createTrip(CreateTripRequest(newTrip.brand, newTrip.model, newTrip.roadNumber))
        return dataResponse.toDomainCreateTripResponse()
    }

    override suspend fun getTripRouteForDriver(): TripRoute{
        val routeResponse = tripStorage.getTripRouteForDriver()
        return routeResponse.toDomainTripRouteResponse()
    }

    override suspend fun changePointStatus(id:Long): ChangePointStatusResponse{
        val response =  tripStorage.changePointStatus(ChangePointStatusRequest(id))
        return response.toDomainChangePointStatusResponse();
    }
}

