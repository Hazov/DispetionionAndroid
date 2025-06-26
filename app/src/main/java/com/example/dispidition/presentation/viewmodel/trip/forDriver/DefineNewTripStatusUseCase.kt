package com.example.dispidition.presentation.viewmodel.trip.forDriver

import com.example.domain.model.trip.forDriver.tripRoute.TripRoutePoint

class DefineNewTripStatusUseCase {
    //TODO что тут
    fun execute(currentTripPoint : TripRoutePoint): String{
        return if(currentTripPoint.arrivalDate == null) "ARRIVAL" else "COMPLETE"
    }
}