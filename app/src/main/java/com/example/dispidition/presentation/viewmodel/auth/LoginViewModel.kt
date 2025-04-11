package com.example.dispidition.presentation.viewmodel.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispidition.presentation.screens.auth.LoginScreen
import com.example.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :
    ViewModel() {

    var login = mutableStateOf("")
    var password = mutableStateOf("")

    fun login(){
        viewModelScope.launch(){
            loginUseCase.execute(login.value, password.value)
        }
    }
}