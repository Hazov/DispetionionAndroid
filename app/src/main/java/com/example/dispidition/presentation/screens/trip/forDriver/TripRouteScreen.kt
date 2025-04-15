package com.example.dispidition.presentation.screens.trip.forDriver

import android.text.Layout
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.trip.forDriver.TripRouteViewModel
import com.example.dispidition.R
import com.example.domain.model.trip.details.TripDetails
import com.example.domain.model.trip.details.TripDetailsCargoPoint
import com.example.domain.model.trip.forDriver.tripRoute.TripRoutePoint
import com.example.ui.details.DetailsUI

class TripRouteScreen(val detailsUI: DetailsUI, val navController: NavHostController) {

    @Composable
    fun Init(vm: TripRouteViewModel = hiltViewModel()) {
        vm.fetchRoute()
        Show(vm)
    }

    @Composable
    fun Show(vm: TripRouteViewModel) {
        val currentPoint = vm.currentTripPoint
        if (currentPoint != null) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item() {
                    detailsUI.DetailsContainer {
                        if (vm.prevTripPoints.isNotEmpty()) {
                            ExtendablePointCardList(vm, false)
                        }

                        Box(
                            modifier = Modifier.fillMaxSize(),
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
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(if (!isExpand.value) R.drawable.expand else R.drawable.collapse),
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
        detailsUI.DetailsCard {
            Column {
                detailsUI.DetailsPairRow("Статус точки:", pointStatus)
                detailsUI.DetailsPairRow(
                    if (point.type == "UNLOAD") "Разгрузить" else "Загрузить",
                    point.cargoName
                )
                detailsUI.DetailsPairRow("По адресу:") {
                    Row {
                        Text("Город:")
                        Text(text = point.address.city)
                    }
                    Row {
                        Text("Улица:")
                        Text(text = point.address.street)
                    }
                    Row {
                        Text("Дом:")
                        Text(text = point.address.house)
                    }
                }

                detailsUI.DetailsPairRow("Показать на карте:") {
                    IconButton(modifier = Modifier.size(70.dp), onClick = {}) {
                        Icon(painterResource(R.drawable.gpsreporticon), "Карта")
                    }
                }
            }
        }
    }


    @Composable
    fun PointsLine(trip: TripDetails, points: List<TripDetailsCargoPoint>) {
        Box(
            Modifier
                .horizontalScroll(rememberScrollState())
                .width((points.size * 120).dp)
                .padding(vertical = 30.dp)
        ) {

            Row(
                modifier = Modifier
                    .zIndex(9000f)
                    .offset(x = 40.dp)
            ) {
                trip.cargos.flatMap { cargo -> cargo.points }
                    .sortedBy { point -> point.serialNumber }.forEach({ point ->
                        val backgroundColor = when (point.isCompleted) {
                            true -> Color(0xff2a711f)
                            false -> Color(0xff24ccc6)
                        }
                        val iconLoadTypeColor =
                            if (point.type.equals("UPLOAD")) Color(0xffc56d27) else Color(0xff196646)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                modifier = Modifier
                                    .padding(horizontal = 25.dp)
                                    .background(color = backgroundColor, shape = CircleShape)
                                    .size(50.dp),
                                onClick = {}
                            ) {

                                Icon(
                                    modifier = Modifier.fillMaxSize(0.5f),
                                    tint = iconLoadTypeColor,
                                    painter = painterResource(
                                        if (point.type.equals("UPLOAD")) R.drawable.uploadicon
                                        else R.drawable.unloadicon
                                    ), contentDescription = "Загрузка"
                                )
                            }
                            Text(textAlign = TextAlign.Center, text = point.address.city)
                        }
                    })

            }

            Canvas(
                Modifier
                    .width((points.size * 120).dp)
                    .height(50.dp)
                    .background(Color.Transparent)
            ) {

                val height = size.height
                val width = size.width
                drawLine(
                    start = Offset(x = 0f, y = height / 2),
                    end = Offset(x = width, y = height / 2),
                    color = Color.DarkGray,
                    strokeWidth = 5.0f,
                )
            }


        }
    }

    @Composable
    fun ChangeStatusButton(action: String, onClick: () -> Unit) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        Button(
            modifier = Modifier
                .height(80.dp)
                .offset(y = 40.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffff7e00)),
            onClick = { onClick() }) {
            Text(text = action)
        }
//        }

    }


}

