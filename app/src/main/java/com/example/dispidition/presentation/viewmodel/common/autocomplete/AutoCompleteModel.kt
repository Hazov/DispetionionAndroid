package com.example.dispidition.presentation.viewmodel.common.autocomplete

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dispidition.presentation.viewmodel.trip.create_trip.CreateTripCargoView

class AutoCompleteModel<T: AutoCompletable>{

    val listModels: MutableSet<T>
    var choice: T?
    var isExpandedCargoAutoComplete:  MutableState<Boolean>
    var dropDownSearchLine:MutableState<String>

    constructor(models: MutableSet<T>) {
        isExpandedCargoAutoComplete = mutableStateOf<Boolean>(false)
        dropDownSearchLine = mutableStateOf<String>("")
        choice = null
        listModels = models
    }

    inline fun <reified T: Instantiatable> createInstance(text: String) : T {
        if(T::class == CreateTripCargoView::class){
            return CreateTripCargoView(text) as T;
        }
        throw Exception()
    }
}