package com.example.dispidition.presentation.viewmodel.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.trip.TripDetails
import com.example.domain.model.truck.TruckDetails
import com.example.domain.usecase.trip.GetTripUseCase
import com.example.domain.usecase.truck.GetTruckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripDetailsViewModel @Inject constructor(private val getTripUseCase: GetTripUseCase) :
    ViewModel() {

    private var _trip = MutableLiveData<TripDetails>()
    var trip: LiveData<TripDetails> = _trip

    fun fetchTruck(id: Long) {
        viewModelScope.launch {
            _trip.value = getTripUseCase.execute(id)
        }
    }

}
