package com.example.dispidition.app.global

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.domain.usecase.auth.FetchPermissionsUseCase
import javax.inject.Inject


class GlobalSettings () {
    var authenticated = mutableStateOf(false)
    var permissions = mutableStateListOf<String>()


    //androidSettings
    var accessFineLocation = mutableStateOf(false)


    fun updatePermissions(){
//        fetchPermissionsUseCase.execute()


    }

}