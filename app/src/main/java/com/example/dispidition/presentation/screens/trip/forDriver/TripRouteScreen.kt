package com.example.dispidition.presentation.screens.trip.forDriver

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.trip.forDriver.TripRouteViewModel
import com.example.dispidition.R
import com.example.dispidition.app.global.GlobalSettings
import com.example.domain.model.trip.forDriver.tripRoute.TripRoutePoint
import com.example.ui.details.DetailsUI
import com.example.ui.entites.trip.TripDetailsCargoPointUIModel
import com.example.ui.entites.trip.TripUI

class TripRouteScreen(
    val detailsUI: DetailsUI,
    val tripUI: TripUI,
    val globalSettings: GlobalSettings,
    val navController: NavHostController
) {

    @Composable
    fun Init(vm: TripRouteViewModel = hiltViewModel()) {
        if(!globalSettings.authenticated.value){
            navController.navigate("login")
        }
        vm.fetchRoute()
        Show(vm)
    }

    @Composable
    fun Show(vm: TripRouteViewModel) {
        val currentPoint = vm.currentTripPoint.value
        val tripRoute = vm.tripRoute
        if (tripRoute != null && currentPoint != null) {
            val sortedUIPoints = tripRoute.points.sortedBy { it.serialNumber }
                .map { TripDetailsCargoPointUIModel(it.type, it.isCompleted, it.address.city) }

            Column() {
                tripUI.PointsLine(sortedUIPoints, R.drawable.unloadicon, R.drawable.uploadicon)
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item() {
                        detailsUI.DetailsContainer {
                            if (vm.prevTripPoints.isNotEmpty()) {
                                ExtendablePointCardList(vm, false)
                            }

                            Box(
                                modifier = Modifier.fillMaxSize().padding(bottom = 50.dp),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                TripPointCard(vm, currentPoint, "Текущая")
                                ChangeStatusButton(vm.getActionText(currentPoint)) {
                                    vm.changeStatus()
                                }
                            }

                            if (vm.futureTripPoints.isNotEmpty()) {
                                ExtendablePointCardList(vm, true)
                            }

                        }
                    }

                }
            }
        } else {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text("У вас нет текущих поездок")
            }
        }
    }


    @Composable
    fun ExtendablePointCardList(vm: TripRouteViewModel, isFuture: Boolean) {
        var isExpand = if (!isFuture) vm.isExpandPrevList else vm.isExpandFutureList
        Card(modifier = Modifier.fillMaxWidth(), onClick = { isExpand.value = !isExpand.value }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(if (!isFuture) "Пройденные точки" else "Будущие точки")
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(if (!isExpand.value) R.drawable.down else R.drawable.up),
                    contentDescription = "Открыть/Скрыть"
                )


            }
        }

        if (isExpand.value) {
            Column {
                for (point in if (!isFuture) vm.prevTripPoints else vm.futureTripPoints) {
                    TripPointCard(vm, point, if (!isFuture) "пройдена" else "в будущем")
                }

            }
        }
    }


    @Composable
    fun TripPointCard(vm: TripRouteViewModel, point: TripRoutePoint, pointStatus: String) {
        val uriHandler = LocalUriHandler.current
        detailsUI.DetailsCard {
            Column {
                detailsUI.DetailsPairRow("Статус точки:", pointStatus)
                detailsUI.DetailsPairRow(
                    if (point.type == "UNLOAD") "Разгрузить" else "Загрузить",
                    point.cargoName
                )
                detailsUI.DetailsPairRow("По адресу:") {
                    Text("Город: ${point.address.city}")
                    Text("Улица: ${point.address.street}")
                    Text("Дом: ${point.address.house} ")
                }

                detailsUI.DetailsPairRow("Показать на карте:") {
                    IconButton(modifier = Modifier.size(60.dp).border(1.dp, Color.Black, CircleShape), onClick = {
                        var address =
                            "${point.address.city} ${point.address.street} ${point.address.house}"
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


    @Composable
    fun ChangeStatusButton(action: String, onClick: () -> Unit) {
        Button(
            modifier = Modifier
                .height(80.dp)
                .offset(y = 40.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffff7e00)),
            onClick = { onClick() }) {
            Text(text = action)
        }
    }


}

