package com.example.ui.autocomplete

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

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
}