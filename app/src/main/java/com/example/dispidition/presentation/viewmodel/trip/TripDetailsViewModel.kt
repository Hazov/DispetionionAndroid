package com.example.dispidition.presentation.viewmodel.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.trip.details.TripDetails
import com.example.domain.usecase.trip.GetTripUseCase
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
               _trip.value?.cargos?.forEach{it.points = it.points.sortedBy { it.serialNumber }}
            }
        }
    }

}



