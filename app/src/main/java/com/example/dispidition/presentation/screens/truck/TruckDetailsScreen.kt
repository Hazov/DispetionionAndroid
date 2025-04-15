package com.example.dispidition.presentation.screens.truck

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dispidition.presentation.viewmodel.truck.TruckDetailsViewModel
import com.example.ui.details.DetailsUI


class TruckDetailsScreen(val detailsUI: DetailsUI, val navController: NavHostController) {

    @Composable
    fun Init(truckId: Long?, vm: TruckDetailsViewModel = hiltViewModel()) {
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



