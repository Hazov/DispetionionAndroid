package com.example.dispidition.presentation.screens.person

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.app.global.GlobalSettings
import com.example.dispidition.presentation.viewmodel.person.CreatePersonViewModel
import main.java.com.example.ui.create.CreateUI

class CreatePersonScreen (val createUI: CreateUI, val globalSettings: GlobalSettings, val navController: NavHostController) {

    @Composable
    fun Init(vm: CreatePersonViewModel = hiltViewModel()) {
        if(!globalSettings.authenticated.value){
            navController.navigate("login")
        }
        Show(vm)
    }

    @Composable
    fun Show(vm: CreatePersonViewModel) {
        
        val person = vm.person

        createUI.ScreenContainer {
            createUI.Header("Создание пользователя")

            Spacer(Modifier.padding(vertical = 10.dp))

            createUI.CreateCard{
                createUI.FieldInCreateCard(fieldName = "Имя", state = person.firstName)
                createUI.FieldInCreateCard(fieldName = "Фамилия", state = person.lastName)
                createUI.FieldInCreateCard(fieldName = "Отчество", state = person.middleName)
                createUI.FieldInCreateCard(fieldName = "Email", state = person.email)
            }
            createUI.CreateButton(onClick = {vm.createPerson()})
        }
    }
}