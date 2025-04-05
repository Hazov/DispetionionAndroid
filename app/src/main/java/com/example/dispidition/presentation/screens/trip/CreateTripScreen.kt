package com.example.dispidition.presentation.screens.trip


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.trip.CreateTripViewModel
import com.example.dispidition.presentation.viewmodel.trip.Point


class CreateTripScreen(val navController: NavHostController) {


    @Composable
    fun Init(vm: CreateTripViewModel = hiltViewModel()) {
        Show(vm)
    }

    @Composable
    fun Show(vm: CreateTripViewModel) {

        var points = vm.points
        var tex = vm._text


        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            item() {
                //Заголовок Создание авто
                Row(
                    modifier = Modifier.fillMaxWidth().background(color = Color.White),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(15.dp),
                        text = "Создание авто",
                        fontSize = 30.sp
                    )
                }
                Spacer(Modifier.padding(vertical = 10.dp))

                //Карточка "водитель и авто"
                Card(
                    modifier = Modifier
                        .fillMaxWidth().padding(bottom = 15.dp),
                    elevation = CardDefaults.cardElevation(
                        3.dp
                    ),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        Modifier.fillMaxWidth().padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //Заголовок карточки
                        Row {
                            Text(fontSize = 16.sp, text= "Авто/водитель")
                        }

                        //Поле "Авто"
                        Column(modifier = Modifier.padding(vertical = 10.dp)) {
                            Text(modifier = Modifier.padding(bottom = 5.dp), text = "Авто")
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = "",
                                onValueChange = { it },
                                label = { Text(text = "Авто") })
                        }
                        //Поле "Водитель"
                        Column(modifier = Modifier.padding(vertical = 10.dp)) {
                            Text(modifier = Modifier.padding(bottom = 5.dp), text = "Водитель")
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = "",
                                onValueChange = { it },
                                label = { Text(text = "Водитель") })
                        }
                    }
                }

                //Карточка загрузки/разгрузки
                for(point in points){
                    Card(modifier = Modifier
                        .fillMaxWidth().padding(bottom = 15.dp),
                        elevation = CardDefaults.cardElevation(
                            3.dp
                        ),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)) {

                        Column(
                            Modifier.fillMaxWidth().padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            //Заголовок карточки
                            Row {
                                Text(fontSize = 16.sp, text= if(point.type.equals("UPLOAD")) "Загрузка" else "Разгрузка")
                            }

                            //Заголовок "Адрес"
                            Row {
                                Text(text= "Адрес")
                            }
                            //Поле "Город"
                            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                                Text(modifier = Modifier.padding(bottom = 5.dp), text = "Город")
                                TextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = point.city.value,
                                    onValueChange = { point.city.value = it },
                                    label = { Text(text = "Город") })
                            }

                            //Поле "Улица"
                            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                                Text(modifier = Modifier.padding(bottom = 5.dp), text = "Улица")
                                TextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = point.street.value,
                                    onValueChange = {point.street.value = it },
                                    label = { Text(text = "Улица") })
                            }

                            //Поле "Дом"
                            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                                Text(modifier = Modifier.padding(bottom = 5.dp), text = "Дом")
                                TextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = point.house.value,
                                    onValueChange = { point.house.value = it },
                                    label = { Text(text = "Дом") })
                            }
                        }


                    }
                }
                Row {
                    Button(onClick = {
                        points.add(Point("UPLOAD"))
                    }) {
                        Text("Загрузиться")
                    }
                    Button(onClick = {
                        points.add(Point("UNLOAD"))
                    }) {
                        Text("Разгрузиться")
                    }
                }

            }

        }
    }
}


