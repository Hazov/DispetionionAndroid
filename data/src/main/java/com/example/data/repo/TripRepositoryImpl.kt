package com.example.data.repo


import android.location.Location
import com.example.data.db.dao.ChangeStatusRequestDao
import com.example.data.db.entity.ChangeStatusRequestRoom
import com.example.data.db.entity.LocationRequestRoom
import com.example.data.model.trip.create.CreateTripRequest
import com.example.data.model.trip.forDriver.changeStatus.request.ChangeStatusRequest
import com.example.data.model.trip.forDriver.changeStatus.request.ChangeTripGpsDataReq
import com.example.data.storage.TripStorage
import com.example.domain.model.trip.create.CreateTripResponse
import com.example.domain.model.trip.create.NewTrip
import com.example.domain.model.trip.details.TripDetails
import com.example.domain.model.trip.forDriver.tripRoute.TripRoute
import com.example.domain.model.trip.registry.RegistryTrip
import com.example.domain.model.trip.tripgps.TripGps
import com.example.domain.repository.TripRepository

class TripRepositoryImpl(private val tripStorage: TripStorage, val changeStatusRequestDao: ChangeStatusRequestDao) : TripRepository {
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
        roomChangeStatus(id, gpsCoordinates, newStatus)
        serverChangeStatus(id, gpsCoordinates, newStatus)
    }

    fun roomChangeStatus(pointId: Long, gpsCoordinates: Location?, newStatus: String){
        val userId = 1L
        var locationRoom: LocationRequestRoom? = null;
        if(gpsCoordinates != null){
            locationRoom = LocationRequestRoom(null, gpsCoordinates.latitude, gpsCoordinates.longitude)
        }
        val changeStatusRequestRoom = ChangeStatusRequestRoom(null, userId, pointId, locationRoom, newStatus )
        changeStatusRequestDao.insert(changeStatusRequestRoom)
    }

    suspend fun serverChangeStatus(id: Long, gpsCoordinates: Location?, newStatus: String){
        var coordinatesRequest: ChangeTripGpsDataReq? = null
        if(gpsCoordinates != null){
            coordinatesRequest = ChangeTripGpsDataReq(gpsCoordinates.latitude, gpsCoordinates.longitude)
        }
        val changeStatusRequest = ChangeStatusRequest(id, newStatus, coordinatesRequest)
        tripStorage.changePointStatus(changeStatusRequest)

    }


    override suspend fun getTripGpsData(tripId: Long): List<TripGps> {
        val response = tripStorage.getTripGpsData(tripId)
        return response.gpsData.map { TripGps(it.type, it.latitude, it.longitude, it.sendDate) }
    }
}

