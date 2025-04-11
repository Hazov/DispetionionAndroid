package com.example.dispidition.presentation.screens.trip


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.common.autocomplete.AutoCompletable
import com.example.dispidition.presentation.viewmodel.common.autocomplete.AutoComplete
import com.example.dispidition.presentation.viewmodel.common.autocomplete.AutoCompleteModel
import com.example.dispidition.presentation.viewmodel.common.autocomplete.Instantiatable
import com.example.dispidition.presentation.viewmodel.trip.create_trip.CreateTripCargoView
import com.example.dispidition.presentation.viewmodel.trip.create_trip.CreateTripViewModel


class CreateTripScreen (val navController: NavHostController) {


    @Composable
    fun Init(vm: CreateTripViewModel = hiltViewModel()) {

        vm.fetchTrucks()
        vm.fetchDrivers()
        Show(vm)
    }

    @Composable
    fun Show(vm: CreateTripViewModel) {
        val autocomplete = AutoComplete()
        val uriHandler = LocalUriHandler.current
        var points = vm.pointViews


        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            item() {
                //Заголовок Создание авто
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White),
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
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    elevation = CardDefaults.cardElevation(
                        3.dp
                    ),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //Заголовок карточки
                        Row {
                            Text(fontSize = 16.sp, text = "Авто/водитель")
                        }

                        autocomplete.AutoCompleteTextField(vm.truckAC, textFieldName = "Авто", placeholder = "Наименование авто")
                        autocomplete.AutoCompleteTextField(vm.driverAC, textFieldName = "Водитель", placeholder = "Имя водителя")

                    }
                }

                //Карточка загрузки/разгрузки
                for (point in points) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        elevation = CardDefaults.cardElevation(
                            3.dp
                        ),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {

                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            //Заголовок карточки
                            Row {
                                Text(
                                    fontSize = 16.sp,
                                    text = if (point.type.equals("UPLOAD")) "Загрузка" else "Разгрузка"
                                )
                            }

                            autocomplete.AutoCompleteTextField(point.cargoAC, textFieldName = "Груз", placeholder = "Наименование груза")

                            //Заголовок "Адрес"
                            Row {
                                Text(text = "Адрес")
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
                                    onValueChange = { point.street.value = it },
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
                            if (point.city.value.isNotEmpty() && point.street.value.isNotEmpty() && point.house.value.isNotEmpty()) {
                                Row {
                                    Button(onClick = {
                                        var address =
                                            "${point.city.value} ${point.street.value} ${point.house.value}"
                                        address = address.replace(" ", "%20")
                                        uriHandler.openUri("https://yandex.ru/maps/2/saint-petersburg/search/${address}")
                                    }) {
                                        Text("Посмотреть на карте")
                                    }
                                }
                            }

                        }


                    }
                }
                Row {
                    Button(onClick = {
                        points.add(vm.createPointView("UPLOAD"))
                    }) {
                        Text("Загрузиться")
                    }
                    if (points.size > 0) {
                        Button(onClick = {
                            points.add(vm.createPointView("UNLOAD"))
                        }) {
                            Text("Разгрузиться")
                        }
                    }

                }
                Row{
                    Button(onClick = {vm.createTrip()}){
                        Text("Создать")
                    }
                }

            }

        }

    }


}


