package com.example.dispidition.presentation.viewmodel.trip

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import com.example.domain.usecase.trip.CreateTripUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


import com.example.dispidition.presentation.screens.trip.CreateTripScreen
@HiltViewModel
class CreateTripViewModel @Inject constructor(private val createTripUseCase: CreateTripUseCase) :
    ViewModel() {

    val points = mutableStateListOf<Point>()
    val cargos = mutableSetOf<Cargo>()

    fun createTrip() {
//        createTripUseCase.execute()
    }
}



class Point {
    val type: String
    val city: MutableState<String>
    val street: MutableState<String>
    val house: MutableState<String>
    var cargo: Cargo?
    var isExpandedCargoAutoComplete:  MutableState<Boolean>
    var dropDownSearchLine:MutableState<String>

    constructor(_type: String) {
        type = _type
        city = mutableStateOf<String>("")
        street = mutableStateOf<String>("")
        house = mutableStateOf<String>("")
        cargo = null
        isExpandedCargoAutoComplete = mutableStateOf<Boolean>(false)
        dropDownSearchLine = mutableStateOf<String>("")
    }
}

class Cargo {
    val name: MutableState<String>

    constructor(_name: String){
        name = mutableStateOf(_name)
    }


    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as Cargo

        if (name.value != other.name.value) return false

        return true
    }

    override fun hashCode(): Int {
        return name.value.hashCode()
    }
}