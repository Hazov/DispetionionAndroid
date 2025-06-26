package com.example.data.repo


import android.location.Location
import com.example.data.db.dao.TripRouteDao
import com.example.data.model.room.ChangeStatusRequestRoom
import com.example.data.model.trip.create.CreateTripRequest
import com.example.data.model.trip.forDriver.changeStatus.request.ChangePointStatusRequest
import com.example.data.model.trip.forDriver.changeStatus.request.ChangeTripGpsDataReq
import com.example.data.storage.TripStorage
import com.example.domain.model.trip.create.CreateTripResponse
import com.example.domain.model.trip.create.NewTrip
import com.example.domain.model.trip.details.TripDetails
import com.example.domain.model.trip.forDriver.tripRoute.TripRoute
import com.example.domain.model.trip.registry.RegistryTrip
import com.example.domain.model.trip.tripgps.TripGps
import com.example.domain.repository.TripRepository

class TripRepositoryImpl(private val tripStorage: TripStorage, private val tripRouteDao: TripRouteDao) : TripRepository {
    override suspend fun getTrip(id: Long): TripDetails {
        return tripStorage.getTrip(id).tripDetails.toDomainTripDetails()
    }

    override suspend fun getTrips(): List<RegistryTrip> {
        return tripStorage.getTrips().trips.map { trip -> trip.toDomainTripDetails() }
    }

    override suspend fun createTrip(newTrip: NewTrip): CreateTripResponse {
        var dataResponse = tripStorage.createTrip(
            CreateTripRequest(
                newTrip.cargos,
                newTrip.truckId,
                newTrip.driverId
            )
        )
        return dataResponse.toDomainCreateTripResponse()
    }

    override suspend fun getTripRouteForDriver(): TripRoute {
        val routeResponse = tripStorage.getTripRouteForDriver()
        return routeResponse.toDomainTripRouteResponse()
    }

    override suspend fun changePointStatus(id: Long, gpsCoordinates: Location?, newStatus: String) {
        var coordinatesRequest: ChangeTripGpsDataReq? = null
        if(gpsCoordinates != null){
            coordinatesRequest = ChangeTripGpsDataReq(gpsCoordinates.latitude, gpsCoordinates.longitude)
        }
        val pointStatusRequest = ChangePointStatusRequest(id, newStatus, coordinatesRequest)
        val changeStatusRequestRoom = pointStatusRequest.tooooo
        //Сохраняем в room
        tripRouteDao.saveTripRoute(pointStatusRequest)
        //Сохраняем на сервер
        tripStorage.changePointStatus(pointStatusRequest)
    }

    override suspend fun getTripGpsData(tripId: Long): List<TripGps> {
        val response = tripStorage.getTripGpsData(tripId)
        return response.gpsData.map { TripGps(it.type, it.latitude, it.longitude, it.sendDate) }
    }
}

