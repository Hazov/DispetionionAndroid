package com.example.dispidition.presentation.screens.truck

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dispidition.app.global.GlobalSettings
import com.example.dispidition.presentation.viewmodel.truck.TruckDetailsViewModel
import com.example.ui.details.DetailsUI


class TruckDetailsScreen(val detailsUI: DetailsUI, val globalSettings: GlobalSettings, val navController: NavHostController) {

    @Composable
    fun Init(truckId: Long?, vm: TruckDetailsViewModel = hiltViewModel()) {
        if(!globalSettings.authenticated.value){
            navController.navigate("login")
        }
        if (truckId != null) {
            vm.fetchTruck(truckId)
            Show(vm);
        }
    }


    @Composable
    fun Show(vm: TruckDetailsViewModel) {

        val truck = vm.truck.observeAsState().value

        detailsUI.DetailsContainer {
            detailsUI.Header("Информация о поездке")
            detailsUI.DetailsCard("Общая информация") {
                detailsUI.DetailsPairRow("Марка", truck?.brand.orEmpty())
                detailsUI.DetailsPairRow("Модель", truck?.model.orEmpty())
                detailsUI.DetailsPairRow("Номер", truck?.roadNumber.orEmpty())
                detailsUI.DetailsPairRow("Владелец", truck?.ownerName.orEmpty())
            }
        }
    }
}



