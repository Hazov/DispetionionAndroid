package com.example.dispidition.presentation.viewmodel.trip.forDriver

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.trip.forDriver.tripRoute.TripRoute
import com.example.domain.model.trip.forDriver.tripRoute.TripRoutePoint
import com.example.domain.usecase.trip.forDriver.ChangePointStatusUseCase
import com.example.domain.usecase.trip.forDriver.GetTripRouteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripRouteViewModel @Inject constructor(
    private val getTripRouteUseCase: GetTripRouteUseCase,
    private val changePointStatusUseCase: ChangePointStatusUseCase
) : ViewModel() {

    var tripRoute: TripRoute? = null;

    var isExpandPrevList = mutableStateOf(false)
    var isExpandFutureList = mutableStateOf(false)

    var prevTripPoints = ArrayList<TripRoutePoint>()
    var currentTripPoint: TripRoutePoint? = null
    var futureTripPoints = ArrayList<TripRoutePoint>()

    fun fetchRoute(){
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            tripRoute = null
        }
        viewModelScope.launch(exceptionHandler) {
            tripRoute = getTripRouteUseCase.execute()
            convert()
        }

    }

    fun convert(){
        currentTripPoint = null
        prevTripPoints.clear()
        futureTripPoints.clear()
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
            currentTripPoint = point
        })
    }

    fun changeStatus(){
        changePointStatusUseCase.execute(currentTripPoint!!.id)
    }

    fun getActionText(point: TripRoutePoint): String{
        if (point.type == "UNLOAD") {
            if (point.arrivalForUnloadingDate == null)
                return "Приехал на разгрузку"
            else
                return "Разгрузился"
        } else {
            if (point.arrivalForUploadingDate == null)
                return "Приехал на загрузку"
            else
                return "загрузился"
        }

    }

}
