package com.example.dispidition.presentation.screens.trip


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.R
import com.example.dispidition.app.global.GlobalSettings
import com.example.dispidition.presentation.viewmodel.trip.trip_details.TripDetailsViewModel
import com.example.domain.model.trip.details.TripDetails
import com.example.domain.model.trip.details.TripDetailsCargoPoint
import com.example.ui.details.DetailsUI
import com.example.ui.entites.trip.TripDetailsCargoPointUIModel
import com.example.ui.entites.trip.TripUI
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class TripDetailsScreen(
    val detailsUI: DetailsUI,
    val tripUI: TripUI,
    val globalSettings: GlobalSettings,
    val navController: NavHostController,
) {
    var formatter = DateTimeFormatter.ofPattern("dd.MM")

    @Composable
    fun Init(id: Long?, vm: TripDetailsViewModel = hiltViewModel()) {
        if(!globalSettings.authenticated.value){
            navController.navigate("login")
        }
        if (id != null) {
            vm.fetchTrip(id)
            Show(vm);
        }
    }

    @Composable
    fun Show(vm: TripDetailsViewModel) {

        val trip = vm.trip.observeAsState().value

        if (trip != null) {
            val sortedPoints = trip.cargos.flatMap { cargo -> cargo.points }
                .sortedBy { point -> point.serialNumber }
            val sortedPointsUI = sortedPoints.map { point ->
                TripDetailsCargoPointUIModel(point.type, point.isCompleted, point.address.city)
            }.toList()

            detailsUI.DetailsContainer {
                TripHeader(trip, sortedPoints)

                //Линия точек
                tripUI.PointsLine(sortedPointsUI, R.drawable.unloadicon, R.drawable.uploadicon)

                Button(onClick = {navController.navigate("tripGps/${trip.id}")}) {
                    Text("GPS данные по поездке")
                }

                LazyColumn {
                    item() {
                        //Информация о водителе и авто
                        DriverAndTruckInfo(trip)
                        //Информация о грузах
                        PointsOfCargo(trip)
                    }
                }
            }

        }


    }

    @Composable
    fun TripHeader(trip: TripDetails, sortedPoints: List<TripDetailsCargoPoint>) {
        Row {
            Text(
                text = "${
                    formatter.format(
                        trip.creationDate.toInstant().atOffset(ZoneOffset.UTC)
                            .toLocalDateTime()
                    )
                } - ${
                    if (sortedPoints.last().completionDate == null) "наст.вр" else formatter.format(
                        sortedPoints.last().completionDate?.toInstant()
                            ?.atOffset(ZoneOffset.UTC)?.toLocalDateTime()
                    )
                }",
                fontSize = 18.sp
            )
        }
        Row {
            Text(
                text = "${sortedPoints.first().address.city} - ${sortedPoints.last().address.city}",
                fontSize = 26.sp
            )
        }
    }


    @Composable
    fun PointsOfCargo(trip: TripDetails) {
        val uriHandler = LocalUriHandler.current
        Row(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), horizontalArrangement = Arrangement.Center) {
            Text(text = "Информация о грузах", fontSize = 20.sp)
        }

        if (trip.cargos.isNotEmpty()) {
            Column {
                for (cargo in trip.cargos) {
                    detailsUI.DetailsCard(cardHeader = "Груз ${cargo.name}") {
                        detailsUI.DetailsPairRow(leftText = "Заказчик", rightText = cargo.customer.company.name)
                        detailsUI.DetailsPairRow(leftText = "Представитель"){
                            Text(cargo.customer.firstName)
                            Text(cargo.customer.lastName)
                        }

                        Column {
                            for (point in cargo.points) {
                                HorizontalDivider(Modifier.padding(vertical = 15.dp))
                                Text(point.serialNumber.toString())
                                Column {
                                    detailsUI.DetailsPairRow("Адрес") {
                                        Text(point.address.city)
                                        Text(point.address.street)
                                        Text(point.address.house)
                                    }

                                    detailsUI.DetailsPairRow("Адрес"){
                                        Row {
                                            Button(onClick = {
                                                var address =
                                                    "${point.address.city} ${point.address.street} ${point.address.house}"
                                                address = address.replace(" ", "%20")
                                                uriHandler.openUri("https://yandex.ru/maps/2/saint-petersburg/search/${address}")
                                            }) {
                                                Text("На карте")
                                            }
                                        }
                                    }
                                    detailsUI.DetailsPairRow("Тип") {
                                        Row {
                                            Text(if (point.type.equals("UNLOAD")) "Разгрузка" else "Загрузка")
                                            Icon(
                                                modifier = Modifier.size(25.dp).padding(start = 10.dp),
                                                tint = if (point.type.equals("UNLOAD")) Color(0xff1d98e6) else Color(0xffe98b10),
                                                painter = painterResource(
                                                    if (point.type.equals("UNLOAD")) R.drawable.unloadicon else R.drawable.uploadicon
                                                ),
                                                contentDescription = "Разгрузка/Загрузка"
                                            )
                                        }
                                    }
                                    detailsUI.DetailsPairRow("GPS отчет"){
                                        Row {
                                            Button(onClick = {
                                            }) {

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


    @Composable
    fun DriverAndTruckInfo(trip: TripDetails) {
        detailsUI.DetailsCard(cardHeader = "Информация о водителе") {
            detailsUI.DetailsPairRow(leftText = "Водитель"){
                Text(trip.driver.lastName)
                Text(trip.driver.firstName)
            }

            detailsUI.DetailsPairRow(leftText = "Авто"){
                Text("${trip.truck.brand} ${trip.truck.model}")
                Text(trip.truck.roadNumber)
            }
        }
    }


    //TODO в отдельный класс
    @Composable
    fun RowInfo(header: String, content: @Composable () -> Unit) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Row(Modifier.fillMaxWidth(0.5f)) {
                Text(fontWeight = FontWeight.Bold, text = header)
            }
            content()
        }
    }


}