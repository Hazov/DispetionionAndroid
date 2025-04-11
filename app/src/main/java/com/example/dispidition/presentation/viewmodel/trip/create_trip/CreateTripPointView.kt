package com.example.dispidition.presentation.viewmodel.trip.create_trip

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dispidition.presentation.viewmodel.common.autocomplete.AutoCompleteModel

class CreateTripPointView {
    val type: String
    val city: MutableState<String>
    val street: MutableState<String>
    val house: MutableState<String>
    var cargoAC: AutoCompleteModel<CreateTripCargoView>

    constructor(_type: String, createTripCargoViews: MutableSet<CreateTripCargoView>) {
        type = _type
        city = mutableStateOf<String>("")
        street = mutableStateOf<String>("")
        house = mutableStateOf<String>("")
        cargoAC = AutoCompleteModel<CreateTripCargoView>(createTripCargoViews)
    }
}