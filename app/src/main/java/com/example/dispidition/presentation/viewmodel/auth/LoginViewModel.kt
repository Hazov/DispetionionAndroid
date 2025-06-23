package com.example.dispidition.presentation.viewmodel.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.dispidition.app.global.GlobalSettings
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.LogoutUseCase
import com.example.domain.usecase.auth.FetchPermissionsUseCase
import com.example.domain.usecase.auth.GetDeviceTokenUseCase
import com.example.domain.usecase.auth.GetPermissionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getDeviceTokenUseCase: GetDeviceTokenUseCase,
    private val fetchPermissionsUseCase: FetchPermissionsUseCase,
    private val globalsSettings: GlobalSettings
) :
    ViewModel() {

    var login = mutableStateOf("")
    var password = mutableStateOf("")


    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

    }

    fun login(navController: NavHostController) {
        viewModelScope.launch(exceptionHandler) {
            var deviceToken = getDeviceTokenUseCase.execute();
            loginUseCase.execute(login.value, password.value, deviceToken)
            val perm = fetchPermissionsUseCase.execute()
            globalsSettings.permissions.clear()
            globalsSettings.permissions.addAll(perm.toList())
            globalsSettings.authenticated.value = true
            if(globalsSettings.permissions.contains("ADMIN")){
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
            globalsSettings.permissions.clear()
            globalsSettings.authenticated.value = false

        }
    }
}
