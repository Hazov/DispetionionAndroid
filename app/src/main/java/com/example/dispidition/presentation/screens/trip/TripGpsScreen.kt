package com.example.dispidition.presentation.screens.trip

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.R
import com.example.dispidition.app.global.GlobalSettings
import com.example.dispidition.presentation.viewmodel.trip.trip_gps.TripGpsViewModel
import com.example.ui.details.DetailsUI
import java.time.format.DateTimeFormatter

class TripGpsScreen (
    val detailsUI: DetailsUI,
    val globalSettings: GlobalSettings,
    val navController: NavHostController,
    ) {
        var formatter = DateTimeFormatter.ofPattern("dd.MM")

        @Composable
        fun Init(id: Long?, vm: TripGpsViewModel = hiltViewModel()) {
            if(!globalSettings.authenticated.value){
                navController.navigate("login")
            }
            if (id != null) {
                vm.fetchTripGps(id)
                Show(vm);
            }
        }

        @Composable
        fun Show(vm: TripGpsViewModel) {
            val uriHandler = LocalUriHandler.current
            val tripGpses = vm.tripGpses.observeAsState().value
            detailsUI.DetailsContainer {
                LazyColumn {
                    item(){
                        Row {
                            Text(text = "GPS отчет", fontSize = 20.sp)
                        }
                        if (tripGpses != null) {
                            for(gps in tripGpses){
                                detailsUI.DetailsCard {
                                    detailsUI.DetailsPairRow(gps.type){
                                        Row {
                                            Column {
                                                Text(gps.latitude.toString())
                                                Text(gps.longitude.toString())
                                            }
                                            IconButton(modifier = Modifier.size(60.dp).border(1.dp, Color.Black, CircleShape), onClick = {
                                                var address = "${gps.latitude} ${gps.longitude}"
                                                address = address.replace(" ", "%20")
                                                uriHandler.openUri("https://yandex.ru/maps/2/saint-petersburg/search/${address}")
                                            }) {
                                                Icon(modifier = Modifier.size(45.dp),
                                                    painter =  painterResource(R.drawable.ongps),
                                                    contentDescription =  "Карта")
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
}