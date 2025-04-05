package com.example.dispidition.presentation.viewmodel.trip

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.trip.CreateTripUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateTripViewModel @Inject constructor(private val createTripUseCase: CreateTripUseCase) :
    ViewModel() {

    var _text = mutableStateOf("")
    val points = mutableStateListOf<Point>()

}

class Point {
    val type: String
    val city: MutableState<String>
    val street: MutableState<String>
    val house: MutableState<String>

    constructor(_type: String) {
        type = _type
        city = mutableStateOf<String>("")
        street = mutableStateOf<String>("")
        house = mutableStateOf<String>("")
    }
}