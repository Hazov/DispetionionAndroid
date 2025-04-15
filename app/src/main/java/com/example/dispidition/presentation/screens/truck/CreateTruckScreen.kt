package com.example.dispidition.presentation.screens.truck

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.truck.CreateTruckViewModel
import main.java.com.example.ui.create.CreateUI

class CreateTruckScreen(val createUI: CreateUI, val navController: NavHostController) {

    @Composable
    fun Init(vm: CreateTruckViewModel = hiltViewModel()) {
        vm.newTruckModel()
        Show(vm)
    }

    @Composable
    fun Show(vm: CreateTruckViewModel) {

        val truck = vm.truck

        createUI.ScreenContainer {
            createUI.Header("Создание авто")

            Spacer(Modifier.padding(vertical = 10.dp))

            createUI.CreateCard {
                createUI.FieldInCreateCard(fieldName = "Марка", state = truck.brand)
                createUI.FieldInCreateCard(fieldName = "Модель", state = truck.model)
                createUI.FieldInCreateCard(fieldName = "Номер", state = truck.roadNumber)
            }
            createUI.CreateButton { vm.createTruck() }
        }
    }
}