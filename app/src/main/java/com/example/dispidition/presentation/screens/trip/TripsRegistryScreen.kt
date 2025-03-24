package com.example.dispidition.presentation.screens.trip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

class TripsRegistryScreen(val navController: NavHostController) {
    @Composable
    fun Show(modifier: Modifier = Modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
        ) {
            items(
                listOf(
                    Trip(
                        "Спб",
                        "Москва",
                        "ИП Андрей Иванович Петров",
                        "Кушпат Садыров",
                        150000,
                        "В пути",
                        "Dispedition",
                        "К 888 АВ 777"
                    ),
                    Trip(
                        "Москва",
                        "Воронеж",
                        "ИП Иван Логинов Пароль",
                        "Кушпат Садыров",
                        1000000,
                        "Завершена",
                        "Dispedition",
                        "К 888 АВ 777"
                    ),
                    Trip(
                        "Владивосток",
                        "Алма-Аты",
                        "ОАО Молочный завод",
                        "Сидоров Олег",
                        55000,
                        "Завершена",
                        "Dispedition",
                        "К 434 АВ 777"
                    ),
                )
            ) { trip ->
                val stateColor = remember {
                    mutableStateOf(Color.Black)
                }
                if (trip.status.equals("Активна")) {
                    stateColor.value = Color(0xff57b44e);
                } else {
                    stateColor.value = Color(0xffb2b2b2);

                }
                Card(modifier = Modifier
                    .padding(15.dp)
                    .defaultMinSize(minHeight = 110.dp)
                    .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        15.dp
                    ),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    onClick = {navController.navigate("trip")}) {
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
                        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Row {
                                Text(text = trip.customer, fontSize = 17.sp)
                            }
                            Row {
                                Text(text = trip.status, fontSize = 17.sp)
                            }
                        }
                        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Row {
                                Text(text = "Грузоперевозчик: " + trip.transporterCompanyName, fontSize = 12.sp)

                            }
                            Row {
                                Text(text = "(" + trip.driverName + "  " + trip.roadNumber + ")", fontSize = 9.sp)
                            }

                        }
                        Row(verticalAlignment = Alignment.Bottom) {
                            Row {
                                Text(text = trip.fromAddress + " - " + trip.toAddress, fontSize = 16.sp)
                            }
                            Row {
                                Text(text = trip.cost.toString() + "р", fontSize = 16.sp);
                            }

                        }
                    }
                }

            }

        }
    }
}

data class Trip(
    val fromAddress: String,
    val toAddress: String,
    val customer: String,
    val driverName: String,
    val cost: Number,
    val status: String,
    val transporterCompanyName: String,
    val roadNumber: String
)