package com.example.dispidition.presentation.viewmodel.truck

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.truck.TruckDetails
import com.example.domain.usecase.truck.GetTruckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TruckDetailsViewModel @Inject constructor(private val getTruckUseCase: GetTruckUseCase) :
    ViewModel() {

    private var _truck = MutableLiveData<TruckDetails>()
    var truck: LiveData<TruckDetails> = _truck

    fun fetchTruck(id: Long) {
        viewModelScope.launch {
            _truck.value = getTruckUseCase.execute(id)
        }
    }

}
