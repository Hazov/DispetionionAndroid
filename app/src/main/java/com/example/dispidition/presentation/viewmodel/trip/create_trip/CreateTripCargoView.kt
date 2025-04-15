package com.example.dispidition.presentation.viewmodel.trip.create_trip

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.ui.autocomplete.AutoCompletable
import com.example.ui.autocomplete.Instantiatable


class CreateTripCargoView : AutoCompletable, Instantiatable {

    val name: MutableState<String>

    constructor(_name: String){
        name = mutableStateOf(_name)
    }

    override fun getText(): String {
        return name.value
    }

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as CreateTripCargoView

        if (name.value != other.name.value) return false

        return true
    }

    override fun hashCode(): Int {
        return name.value.hashCode()
    }
}