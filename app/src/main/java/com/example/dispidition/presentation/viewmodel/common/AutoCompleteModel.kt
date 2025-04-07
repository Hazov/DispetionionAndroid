package com.example.dispidition.presentation.viewmodel.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class AutoCompleteModel<T> {
    val listModels: MutableSet<T>

    var currentModel: Unit;
    var isExpandedCargoAutoComplete:  MutableState<Boolean>
    var dropDownSearchLine:MutableState<String>

    constructor(_currentModel: Unit, _listModels: Collection<T>?) {

        isExpandedCargoAutoComplete = mutableStateOf<Boolean>(false)
        dropDownSearchLine = mutableStateOf<String>("")
        currentModel = _currentModel
        listModels = mutableSetOf<T>()
        if(_listModels != null){
            listModels.addAll(_listModels)
        }
    }
}