package com.example.dispidition.presentation.viewmodel.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.trip.RegistryTrip
import com.example.domain.model.truck.RegistryTruck
import com.example.domain.usecase.trip.GetTripsUseCase
import com.example.domain.usecase.truck.GetTrucksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripRegistryViewModel@Inject constructor(private val getTripsUseCase: GetTripsUseCase): ViewModel() {
    private var _trips = MutableLiveData< List<RegistryTrip>>()
    var trips: LiveData<List<RegistryTrip>>  = _trips

    fun fetchTrucks() {
        viewModelScope.launch {
            _trips.value = getTripsUseCase.execute();
        }
    }
}