package com.example.dispidition.presentation.viewmodel.trip.trip_gps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.gps.Location
import com.example.domain.usecase.trip.get_trip_gps.GetTripGpsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripGpsViewModel @Inject constructor(private val getTripGpsUseCase: GetTripGpsUseCase) :
    ViewModel() {
    private var _locations = MutableLiveData<List<Location>>()
    var locations: LiveData<List<Location>> = _locations


    fun fetchTripGps(tripId: Long) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

        }
        viewModelScope.launch(exceptionHandler) {
            getTripGpsUseCase.execute(tripId)
        }
    }
}