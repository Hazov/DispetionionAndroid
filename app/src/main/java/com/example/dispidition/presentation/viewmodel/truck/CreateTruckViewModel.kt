package com.example.dispidition.presentation.viewmodel.truck

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.truck.create.NewTruck
import com.example.domain.usecase.truck.CreateTruckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTruckViewModel @Inject constructor(private val createTruckUseCase: CreateTruckUseCase) :
    ViewModel() {
    var truck = Truck()
    fun newTruckModel() {
        truck = Truck()
    }
    fun createTruck(){
        val newTruck = NewTruck(truck.brand.value, truck.model.value, truck.roadNumber.value)
        viewModelScope.launch{
            createTruckUseCase.execute(newTruck)
        }
    }
}

class Truck(
    var brand: MutableState<String> = mutableStateOf<String>(""),
    var model: MutableState<String> = mutableStateOf<String>(""),
    var roadNumber: MutableState<String> = mutableStateOf<String>("")

)