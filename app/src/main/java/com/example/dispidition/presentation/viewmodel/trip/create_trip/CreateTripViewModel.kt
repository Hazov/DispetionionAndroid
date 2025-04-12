package com.example.dispidition.presentation.viewmodel.trip.create_trip

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispidition.presentation.viewmodel.common.autocomplete.AutoCompleteModel
import com.example.domain.model.trip.create.NewAddress
import com.example.domain.model.trip.create.NewCargo
import com.example.domain.model.trip.create.NewPoint
import com.example.domain.model.trip.create.NewTrip

import com.example.domain.usecase.trip.CreateTripUseCase
import com.example.domain.usecase.trip.autocomplete.GetDriversAutoCompleteUseCase
import com.example.domain.usecase.trip.autocomplete.GetTrucksAutoCompleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val createTripUseCase: CreateTripUseCase,
    private val getTrucksAutoCompleteUseCase: GetTrucksAutoCompleteUseCase,
    private val getDriversAutoCompleteUseCase: GetDriversAutoCompleteUseCase
) :
    ViewModel() {

    val pointViews = mutableStateListOf<CreateTripPointView>()
    val cargoViews = mutableSetOf<CreateTripCargoView>()
    val truckViews = mutableSetOf<CreateTripTruckView>()
    val driverViews = mutableSetOf<CreateTripDriverView>()

    var truckAC = AutoCompleteModel<CreateTripTruckView>(truckViews)
    var driverAC = AutoCompleteModel<CreateTripDriverView>(driverViews)

    fun fetchTrucks() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

        }
        viewModelScope.launch(exceptionHandler) {
            val trucks = getTrucksAutoCompleteUseCase.execute()
            truckViews.addAll(trucks.map { truck ->
                CreateTripTruckView(
                    truck.id,
                    truck.brand,
                    truck.model,
                    truck.roadNumber
                )
            })
        }
    }

    fun fetchDrivers() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

        }
        viewModelScope.launch(exceptionHandler) {
            val drivers = getDriversAutoCompleteUseCase.execute()
            driverViews.addAll(drivers.map { driver ->
                CreateTripDriverView(
                    driver.id,
                    driver.firstName,
                    driver.lastName
                )
            })
        }

    }

    fun createTrip() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

        }
        viewModelScope.launch(exceptionHandler) {
            createTripUseCase.execute(newTrip())
        }
    }

    fun createPointView(type: String): CreateTripPointView {
        return CreateTripPointView(type, cargoViews)
    }


    private fun newTrip(): NewTrip {
        validate()
        var serialNumber = 1;

        val cargoPointsMap = pointViews.groupBy({ it.cargoAC }, {
            val address = NewAddress(it.city.value, it.street.value, it.house.value)
            NewPoint(address, it.type, serialNumber++)
        })
        val cargos = cargoPointsMap.entries.map { NewCargo(it.value) }
        return NewTrip(cargos, truckAC.choice!!.id, driverAC.choice!!.id)
    }

    private fun validate() {
        if (truckAC.choice == null) {

        }

        if (driverAC.choice == null) {

        }
    }
}








