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
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.auth.LoginViewModel

class LoginScreen(val navController: NavHostController) {
    @Composable
    fun Init(vm: LoginViewModel = hiltViewModel()) {
        Show(vm)
    }

    @Composable
    fun Show(vm: LoginViewModel) {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Row {
                    Text(text = "Вход", fontSize = 24.sp)
                }
                Card {
                    Column {
                        Row {
                            Text("Email")
                            TextField(value = vm.login.value,
                                onValueChange = {vm.login.value = it},
                                label = { Text("Логин") }
                            )
                        }
                        Row {
                            Text("Пароль")
                            TextField(value = vm.password.value,
                                onValueChange = {vm.password.value = it},
                                label = { Text("Пароль") })
                        }
                        Row {
                            Button(onClick = {vm.login()}) {
                                Text("Войти")
                            }
                        }
                    }
                }

            }
        }


    }

}