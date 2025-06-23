package com.example.dispidition.presentation.viewmodel.trip.trip_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.trip.details.TripDetails
import com.example.domain.model.trip.details.TripDetailsCargo
import com.example.domain.usecase.trip.get_trip.GetTripUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripDetailsViewModel @Inject constructor(private val getTripUseCase: GetTripUseCase) :
    ViewModel() {


    private var _trip = MutableLiveData<TripDetails>()
    var trip: LiveData<TripDetails> = _trip

    fun fetchTrip(id: Long) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

        }
        viewModelScope.launch(exceptionHandler) {
            _trip.value = getTripUseCase.execute(id)
            if(_trip.value != null){
                val cargoComparator = Comparator{ c1: TripDetailsCargo, c2: TripDetailsCargo ->
                    c1.points.map{it.serialNumber}.minBy { it } - c2.points.map{it.serialNumber}.minBy { it }}
                //Сортировка
               _trip.value!!.cargos = _trip.value!!.cargos.sortedWith(cargoComparator).toList()
               _trip.value!!.cargos.forEach{it.points = it.points.sortedBy { it.serialNumber }}
            }
        }
    }

}



