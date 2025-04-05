package com.example.dispidition.presentation.viewmodel.trip.forDriver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.trip.forDriver.tripRoute.TripRoute
import com.example.domain.model.trip.forDriver.tripRoute.TripRoutePoint
import com.example.domain.usecase.trip.forDriver.ChangePointStatusUseCase
import com.example.domain.usecase.trip.forDriver.GetTripRouteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TripRouteViewModel @Inject constructor(
    private val getTripRouteUseCase: GetTripRouteUseCase,
    private val changePointStatusUseCase: ChangePointStatusUseCase
) :
    ViewModel() {

    var tripRoute: TripRoute? = null;

    var isExtendPrevList = false
    var isExtendFutureList = false

    var prevTripPoints = ArrayList<TripRoutePoint>()
    var currentTripPoint: TripRoutePoint? = null
    var futureTripPoints = ArrayList<TripRoutePoint>()

    fun fetchRoute(){
        viewModelScope.launch {
            tripRoute = getTripRouteUseCase.execute()
            convert()
        }

    }

    fun convert(){
        var isCurrentFound = false;
        tripRoute?.points?.forEach({ point ->
            if(!isCurrentFound){
                if(!point.isCompleted){
                    prevTripPoints.add(point)
                } else {
                    currentTripPoint = point;
                    isCurrentFound = true;
                }
            } else {
                futureTripPoints.add(point)
            }
        })
    }

    fun changeStatus(){
        changePointStatusUseCase.execute(currentTripPoint.id)
    }

}
