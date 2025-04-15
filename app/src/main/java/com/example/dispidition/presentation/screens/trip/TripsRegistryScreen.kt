package com.example.dispidition.presentation.screens.trip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.R
import com.example.dispidition.presentation.viewmodel.trip.TripRegistryViewModel
import com.example.domain.model.trip.registry.RegistryTrip
import com.example.ui.registry.RegistryUI
import main.java.com.example.ui.create.CreateUI

class TripsRegistryScreen(
    val createUI: CreateUI,
    val registryUI: RegistryUI,
    val navController: NavHostController
) {

    @Composable
    fun Init(vm: TripRegistryViewModel = hiltViewModel()) {
        vm.fetchTrucks()
        Show(vm);
    }

    @Composable
    fun Show(vm: TripRegistryViewModel) {

        val trips = vm.trips.observeAsState().value
        if (trips != null) {
            registryUI.RegistryContainer {
                Column {
                    for (trip in trips) {
                        registryUI.RegistryCard(
                            onClick = { navController.navigate("trip/${trip.id}") },
                            avatarResource = R.drawable.tripavatar
                        ) {
                            Info(trip)
                        }
                    }
                }
            }
            createUI.CreateIcon(R.drawable.addicon) { navController.navigate("createTrip") }
        }

    }

    @Composable
    fun Info(trip: RegistryTrip) {
        StatusInfo(trip)
        DatesInfo(trip)
        FromToInfo(trip)
        TruckAndDriverInfo(trip)
    }

    @Composable
    fun FromToInfo(trip: RegistryTrip) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = trip.sourceAddress?.city.orEmpty(), fontSize = 17.sp)
            Text(text = trip.destinationAddress?.city.orEmpty(), fontSize = 17.sp)
        }

    }

    @Composable
    fun DatesInfo(trip: RegistryTrip) {
        Row {
            Text(
                text = "НачалоДата - КонецДата",
                fontSize = 11.sp
            )
        }
    }

    @Composable
    fun TruckAndDriverInfo(trip: RegistryTrip) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    text = "Грузоперевозчик: ${trip.truck?.roadNumber.orEmpty()}",
                    fontSize = 12.sp
                )

            }
            Row {
                Text(
                    text = "(${trip.driver?.lastName} ${trip.driver?.firstName} )",
                    fontSize = 9.sp
                )
            }

        }
    }

    @Composable
    fun StatusInfo(trip: RegistryTrip) {

    }
}