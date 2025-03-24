package com.example.dispidition.presentation.screens.trip


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class TripDetailsScreen(val navController: NavHostController) {
    var formatter = DateTimeFormatter.ofPattern("dd.MM")

    @Composable
    fun Show(modifier: Modifier = Modifier) {
        val trip = TripDetails(
            150000.0,
            800.0,
            TripDetailsAddress("Москва", "Седова", "13"),
            TripDetailsAddress("Воронеж", "Ленина", "1к5"),
            TripDetailsCustomer("Молочный завод", "Трубин С.А."),
            TripDetailsSupplier("ИП Хазов Александр Викторович", "Хазов А.В."),
            true,
            false,
            TripDetailsDriver("Сутулов", "Ильшат"),
            TripDetailsTruck("X 555 BH 77", "Volvo", "XX50"),
            LocalDate.now(),
            LocalDate.now(),
            LocalDate.now(),
            LocalDate.now(),
            LocalDate.now()
        )


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp)
        ) {
            //Дата-дата
            Row() {
                Text(
                    text = trip.arrivalForUploadingDate.format(formatter) + " - " + trip.completionUnloadingDate.format(
                        formatter
                    ),
                    fontSize = 18.sp,
                )
            }
            //Заголовок Город-Город
            Row {
                Text(
                    fontSize = 25.sp,
                    text = trip.sourceAddress.city + " - " + trip.destinationAddress.city
                )
            }
            //Год
            Row {
                Text(text = "(" + trip.arrivalForUploadingDate.year.toString() + ")")
            }
            //Линия состояния
            Row {

            }
            Column(horizontalAlignment = Alignment.Start) {
//                Общая информация
                Row {
                    Text(text = "Общая информация");
                }
                Card(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        15.dp
                    ),
                    shape = RoundedCornerShape(10.dp)) {
                    //Заказчик

                    Row(Modifier.fillMaxWidth()) {
                        Text(modifier = Modifier.weight(0.3f),fontSize = 17.sp,text = "Заказчик");
                        Column() {
                            Text(fontSize = 17.sp,text = trip.customer.companyName);
                            Text(fontSize = 17.sp,text = trip.customer.name);
                        }

                    }
                    //Исполнитель
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f),fontSize = 17.sp, text = "Исполнитель");
                        Column() {
                            Text(fontSize = 17.sp, text = trip.supplier.companyName);
                            Text(fontSize = 17.sp, text = trip.supplier.name);
                        }

                    }
                    //Откуда
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Откуда");
                        Column() {
                            Text(fontSize = 17.sp, text = trip.sourceAddress.city);
                            Text(fontSize = 17.sp, text = trip.sourceAddress.street);
                            Text(fontSize = 17.sp, text = trip.sourceAddress.house);
                        }

                    }
                    //Куда
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Куда")
                        Column {
                            Text(fontSize = 17.sp, text = trip.destinationAddress.city)
                            Text(fontSize = 17.sp, text = trip.destinationAddress.street)
                            Text(fontSize = 17.sp, text = trip.destinationAddress.house)
                        }

                    }
                    //Дистанция
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Дистанция")
                        Text(fontSize = 17.sp, text = trip.kmDistance.toString())
                    }
                    //Стоимость
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp, text = "Стоимость")
                        Text(fontSize = 17.sp, text = trip.cost.toString())
                    }
                }
                //Исполнители
                Row {
                    Text(text = "Исполнители")
                }
                Card(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        15.dp
                    ),
                    shape = RoundedCornerShape(10.dp)) {
                    //Водитель
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f),fontSize = 17.sp,text = "Водитель")
                        Text(fontSize = 17.sp,text = trip.driver.lastName + " " + trip.driver.firstName)
                    }
                    //Авто
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp,text = "Авто")
                        Text(fontSize = 17.sp,text = trip.truck.roadNumber)
                        Text(fontSize = 17.sp,text = trip.truck.mark + " " + trip.truck.model)
                    }
                }
                //Состояние
                Row {
                    Text(text = "Состояние")
                }
                Card(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        15.dp
                    ),
                    shape = RoundedCornerShape(10.dp)) {
                    //Статус
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp,text = "Статус")
                        Text(fontSize = 17.sp,text = trip.isCompleted.toString())
                        Image(
                            painter = ColorPainter(color = Color.Green),
                            "dfg",
                            Modifier.size(3.dp)
                        )
                    }
                    //Город1
                    Row {
                        Text(text = trip.sourceAddress.city)
                    }

                    //Начало загрузки
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp,text = "Начало загрузки")
                        Text(fontSize = 17.sp,text = trip.arrivalForUploadingDate.toString())
                    }
                    //Окончание загрузки
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp,text = "Окончание загрузки")
                        Text(fontSize = 17.sp,text = trip.completionUploadingDate.toString())
                    }
                    //Город1
                    Row {
                        Text(text = trip.destinationAddress.city)
                    }
                    //Начало разгрузки
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp,text = "Начало разгрузки")
                        Text(fontSize = 17.sp,text = trip.arrivalForUnloadingDate.toString())
                    }
                    //Окончание разгрузки
                    Row(Modifier.fillMaxWidth(),) {
                        Text(modifier = Modifier.weight(0.3f), fontSize = 17.sp,text = "Окончание разгрузки")
                        Text(fontSize = 17.sp,text = trip.completionUnloadingDate.toString())
                    }
                }
            }
        }

    }


}


data class TripDetails(
    val cost: Double,
    val kmDistance: Double,
    val sourceAddress: TripDetailsAddress,
    val destinationAddress: TripDetailsAddress,
    val customer: TripDetailsCustomer,
    val supplier: TripDetailsSupplier,
    val isCompleted: Boolean,
    val isDeleted: Boolean,
    val driver: TripDetailsDriver,
    val truck: TripDetailsTruck,
    val creationDate: LocalDate,
    val arrivalForUploadingDate: LocalDate,
    val completionUploadingDate: LocalDate,
    val arrivalForUnloadingDate: LocalDate,
    val completionUnloadingDate: LocalDate
)

data class TripDetailsAddress(val city: String, val street: String, val house: String)
data class TripDetailsCustomer(val companyName: String, val name: String)
data class TripDetailsSupplier(val companyName: String, val name: String)
data class TripDetailsTruck(val roadNumber: String, val mark: String, val model: String)
data class TripDetailsDriver(val lastName: String, val firstName: String)