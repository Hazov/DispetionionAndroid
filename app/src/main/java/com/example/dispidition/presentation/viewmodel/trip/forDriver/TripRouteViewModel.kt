package com.example.dispidition.presentation.viewmodel.trip.forDriver

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.trip.forDriver.tripRoute.TripRoute
import com.example.domain.model.trip.forDriver.tripRoute.TripRoutePoint
import com.example.domain.usecase.gps.DefineGpsUseCase
import com.example.domain.usecase.trip.forDriver.ChangeTripStatusUseCase
import com.example.domain.usecase.trip.forDriver.DefineNewTripStatusUseCase
import com.example.domain.usecase.trip.forDriver.GetTripRouteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TripRouteViewModel @Inject constructor(
    private val getTripRouteUseCase: GetTripRouteUseCase,
    private val defineGpsUseCase: DefineGpsUseCase,
    private val defineNewTripStatusUseCase: DefineNewTripStatusUseCase,
    private val changeTripStatusUseCase: ChangeTripStatusUseCase,

    ) : ViewModel() {
    val isChangeStatusDlg: MutableState<Boolean> = mutableStateOf(false)

    var tripRoute: TripRoute? = null;

    var isExpandPrevList = mutableStateOf(false)
    var isExpandFutureList = mutableStateOf(false)

    var prevTripPoints = mutableSetOf<TripRoutePoint>()
    var currentTripPoint = mutableStateOf<TripRoutePoint?>(null)
    var futureTripPoints =mutableSetOf<TripRoutePoint>()

    //init
    fun fetchRoute() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            tripRoute = null
        }
        viewModelScope.launch(exceptionHandler) {
            tripRoute = getTripRouteUseCase.execute()
            convertModelForUi()
        }
    }

    fun changeStatus() {

        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

        }
        viewModelScope.launch(exceptionHandler) {
            val newStatus = defineNewTripStatusUseCase.execute(currentTripPoint.value!!)
            changeTripStatusUseCase.execute(currentTripPoint.value!!.id, newStatus);
            changeStatusInUI(newStatus)

//            fetchRoute()
        }
    }


    fun changeStatusInUI(newStatus: String){
        if (currentTripPoint.value != null) {
            if(newStatus.equals("ARRIVAL")){
                currentTripPoint.value!!.arrivalDate = Date()
            } else if(newStatus.equals("COMPLETE")){
                currentTripPoint.value!!.completionDate = Date()
                prevTripPoints.add(currentTripPoint.value!!)
            }
            currentTripPoint.value!!.isCompleted = true
            currentTripPoint.value = null
            if(!futureTripPoints.isEmpty()){
                currentTripPoint.value = futureTripPoints.first()
                futureTripPoints.remove(currentTripPoint.value)
            }
        }
    }

    fun convertModelForUi() {
        currentTripPoint.value = null
        prevTripPoints.clear()
        futureTripPoints.clear()
        var isCurrentFound = false;
        tripRoute?.points?.sortedBy { it.serialNumber }?.forEach({ point ->
            if (!isCurrentFound) {
                if (point.isCompleted) {
                    prevTripPoints.add(point)
                } else {
                    currentTripPoint.value = point;
                    isCurrentFound = true;
                }
            } else {
                futureTripPoints.add(point)
            }
        })
    }


    fun getActionText(point: TripRoutePoint): String {
        if (point.type == "UNLOAD") {
            if (point.arrivalDate == null)
                return "Приехал на разгрузку"
            else
                return "Разгрузился"
        } else {
            if (point.arrivalDate == null)
                return "Приехал на загрузку"
            else
                return "загрузился"
        }
    }

    fun showDialog() {
        isChangeStatusDlg.value = true
    }

    fun hideChangeStatusDlg() {
        isChangeStatusDlg.value = false
    }

}
