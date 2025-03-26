package com.example.dispidition.presentation.viewmodel.truck_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.truck.TruckDetails
import com.example.domain.usecase.truck.GetTruckUseCase
import kotlinx.coroutines.launch

class TruckDetailsViewModel(
    private val getTruckUseCase: GetTruckUseCase
): ViewModel() {

    private var _truck = MutableLiveData<TruckDetails>()
    var truck: LiveData<TruckDetails>  = _truck

    fun fetchTruck() {
        viewModelScope.launch {
            _truck.value = getTruckUseCase.execute(1L)
        }
    }

}
