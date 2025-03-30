package com.example.dispidition.presentation.screens.trip


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.R
import com.example.dispidition.presentation.viewmodel.trip.TripDetailsViewModel
import com.example.domain.model.trip.details.TripDetails
import com.example.domain.model.trip.details.TripDetailsCargoPoint
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


class TripDetailsScreen(val navController: NavHostController) {
    var formatter = DateTimeFormatter.ofPattern("dd.MM")

    @Composable
    fun Init(id: Long?, vm: TripDetailsViewModel = hiltViewModel()) {
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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Row {
                    Text(
                        text = "${
                            formatter.format(
                                trip.creationDate.toInstant().atOffset(ZoneOffset.UTC)
                                    .toLocalDateTime()
                            )
                        } - ${
                            if (sortedPoints.last().completionUnloadingDate == null) "наст.вр" else formatter.format(
                                sortedPoints.last().completionUnloadingDate?.toInstant()
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

                //Линия точек
                PointsLine(trip, sortedPoints)

                LazyColumn {
                    item() {
                        //Информация о водителе и авто
                        DriverAndTruckInfo(trip)
                        //Информация о грузах
                        PointOfCargo(trip)
                    }
                }


            }

        }


    }


    @Composable
    fun PointOfCargo(trip: TripDetails) {
        Row {
            Text("Информация о грузах")
        }

        if (trip.cargos.isNotEmpty()) {
            Column {
                for (cargo in trip.cargos) {
                    Row {
                        Text("Груз ${cargo.kmDistance}")
                    }
                    CardInfo {
                        RowInfo("Заказчик") {
                            Text(cargo.customer.company.name)
                        }

                        RowInfo("Представитель") {
                            Column {
                                Text(cargo.customer.firstName)
                                Text(cargo.customer.lastName)
                            }
                        }

                        Column {
                            for (point in cargo.points) {
                                HorizontalDivider(Modifier.padding(vertical = 15.dp))
                                Column {
                                    RowInfo("Адрес") {
                                        Column {
                                            Text(point.address.city)
                                            Text(point.address.street)
                                            Text(point.address.house)
                                        }
                                    }
                                    RowInfo("Тип") {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(if(point.type.equals("UNLOAD")) "Разгрузка" else "Загрузка")
                                            Circle(6, if(point.type.equals("UNLOAD")) Color(0x2a711f) else Color.Red)
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
    fun DriverAndTruckInfo(trip: TripDetails) {
        Row(horizontalArrangement = Arrangement.Start) {
            Text("Информация о водителе")
        }

        CardInfo {
            RowInfo("Водитель") {
                Column {
                    Text(trip.driver.lastName)
                    Text(trip.driver.firstName)
                }
            }
            RowInfo("Авто") {
                Column {
                    Text("${trip.truck.brand} ${trip.truck.model}")
                    Text(trip.truck.roadNumber)
                }
            }
        }
    }


    //TODO в отдельный класс
    @Composable
    fun RowInfo(header: String, content: @Composable () -> Unit) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)) {
            Row(Modifier.fillMaxWidth(0.5f)) {
                Text(fontWeight = FontWeight.Bold ,text = header)
            }
            content()
        }
    }

    //TODO в отдельный класс
    @Composable
    fun CardInfo(content: @Composable () -> Unit) {
        Card(
            modifier = Modifier
                .defaultMinSize(minHeight = 110.dp)
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            elevation = CardDefaults.cardElevation(
                3.dp
            ),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Column(Modifier.padding(15.dp)) {
                content()
            }
        }
    }


    @Composable
    fun Circle(size: Int, color: Color){
        Canvas(Modifier.size(size.dp).background(Color.Transparent)) {
            drawCircle(
                color = color,
                center = center,
                radius = size.dp.toPx()
            )
        }
    }


}