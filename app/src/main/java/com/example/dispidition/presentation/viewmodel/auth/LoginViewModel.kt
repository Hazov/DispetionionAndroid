package com.example.dispidition.presentation.viewmodel.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.auth.LoginUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :
    ViewModel() {
    var login = mutableStateOf("")
    var password = mutableStateOf("")

    fun login(){
        loginUseCase.execute(login.value, password.value)
    }
}