package com.example.dispidition.presentation.viewmodel.auth

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.LogoutUseCase
import com.example.domain.usecase.auth.FetchPermissionsUseCase
import com.example.domain.usecase.auth.GetPermissionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val fetchPermissionsUseCase: FetchPermissionsUseCase,
    private val getPermissionsUseCase: GetPermissionsUseCase,
) :
    ViewModel() {

    var login = mutableStateOf("")
    var password = mutableStateOf("")

    var authenticated = mutableStateOf(false)
    var permissions = mutableStateListOf<String>()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

    }

    fun login(navController: NavHostController) {
        authenticated.value = true
        viewModelScope.launch(exceptionHandler) {
            loginUseCase.execute(login.value, password.value)
            val perm = fetchPermissionsUseCase.execute()
            permissions.clear()
            permissions.addAll(perm.toList())
            authenticated.value = true
            if(permissions.contains("ADMIN")){
                navController.navigate("trucks")
            } else {
                navController.navigate("driver_tripRoute")
            }

        }
    }

    fun logout(navController: NavHostController){
        viewModelScope.launch(exceptionHandler) {
            logoutUseCase.execute()
            navController.navigate("login")
        }
    }
}
