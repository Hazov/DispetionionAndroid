package com.example.dispidition.presentation.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dispidition.presentation.viewmodel.auth.LoginViewModel
import com.example.ui.auth.AuthUI
import main.java.com.example.ui.create.CreateUI

class LoginScreen (val authUI: AuthUI, val createUI: CreateUI, val navController: NavHostController) {
    @Composable
    fun Init(vm: LoginViewModel = hiltViewModel()) {
        Show(vm)
    }

    @Composable
    fun Show(vm: LoginViewModel) {

        authUI.AuthContainer{
            authUI.AuthCard{
                authUI.Header("Вход")
                createUI.FieldInCreateCard("Email", state = vm.login)
                createUI.FieldInCreateCard("Пароль", state = vm.password)

                Row {
                    Button(onClick = {vm.login(navController)}) {
                        Text("Войти")
                    }
                }
            }
        }

    }

}