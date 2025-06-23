package com.example.dispidition.presentation.screens.truck

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.R
import com.example.dispidition.app.global.GlobalSettings
import com.example.dispidition.presentation.viewmodel.truck.TrucksRegistryViewModel
import com.example.domain.model.truck.registry.RegistryTruck
import com.example.ui.registry.RegistryUI
import main.java.com.example.ui.create.CreateUI

class TrucksRegistryScreen(
    val createUI: CreateUI,
    val registryUI: RegistryUI,
    val globalSettings: GlobalSettings,
    val navController: NavHostController
) {


    @Composable
    fun Init(vm: TrucksRegistryViewModel = hiltViewModel()) {
        if(!globalSettings.authenticated.value){
            navController.navigate("login")
        }
        vm.fetchTrucks()
        Show(vm);
    }


    @Composable
    fun Show(vm: TrucksRegistryViewModel) {

        val trucks = vm.trucks.observeAsState().value

        registryUI.RegistryContainer {
            if (trucks != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    for (truck in trucks) {
                        registryUI.RegistryCard(
                            onClick = { navController.navigate("truck/${truck.id}") },
                            avatarResource = R.drawable.image
                        ) {
                            Info(truck)
                        }
                    }
                }
            }
        }

        createUI.CreateIcon(R.drawable.addicon) {
            navController.navigate("createTruck")
        }
    }


    @Composable
    fun StateLine() {
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = 110.dp)
                .fillMaxHeight()
                .width(7.dp)
        ) {

        }
    }

    @Composable
    fun Info(truck: RegistryTruck) {
        Text(
            fontSize = 21.sp,
            text = truck.brand + " " + truck.model,
            modifier = Modifier
        )
    }

}

