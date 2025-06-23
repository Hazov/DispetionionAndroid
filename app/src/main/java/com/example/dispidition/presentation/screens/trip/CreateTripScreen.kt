package com.example.dispidition.presentation.screens.trip


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.app.global.GlobalSettings
import com.example.dispidition.presentation.viewmodel.trip.create_trip.CreateTripCargoView
import com.example.ui.autocomplete.AutoComplete
import com.example.dispidition.presentation.viewmodel.trip.create_trip.CreateTripViewModel
import main.java.com.example.ui.create.CreateUI


class CreateTripScreen(
    val createUI: CreateUI,
    val autocomplete: AutoComplete,
    val globalSettings: GlobalSettings,
    val navController: NavHostController
) {


    @Composable
    fun Init(vm: CreateTripViewModel = hiltViewModel()) {
        if(!globalSettings.authenticated.value){
            navController.navigate("login")
        }
        vm.fetchTrucks()
        vm.fetchDrivers()
        Show(vm)
    }

    @Composable
    fun Show(vm: CreateTripViewModel) {
        val uriHandler = LocalUriHandler.current
        var points = vm.pointViews


        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            item() {
                createUI.Header("Создание поездки")

                Spacer(Modifier.padding(vertical = 10.dp))

                createUI.CreateCard("Авто/водитель") {
                    autocomplete.AutoCompleteTextField(
                        vm.truckAC,
                        textFieldName = "Авто",
                        placeholder = "Наименование авто"
                    )
                    autocomplete.AutoCompleteTextField(
                        vm.driverAC,
                        textFieldName = "Водитель",
                        placeholder = "Имя водителя"
                    )
                }

                //Карточка загрузки/разгрузки
                for (point in points) {
                    createUI.CreateCard(if (point.type.equals("UPLOAD")) "Загрузка" else "Разгрузка") {
                        if (point.cargoAC.choice?.name?.value != null) {
                            Row {
                                Text(
                                    text = "(${point.cargoAC.choice?.name?.value})",
                                    fontSize = 11.sp
                                )
                            }
                        }
                        autocomplete.AutoCompleteTextField(
                            point.cargoAC,
                            { name -> CreateTripCargoView(name) },
                            textFieldName = "Груз",
                            placeholder = "Наименование груза"
                        )

                        createUI.FieldInCreateCard(fieldName = "Город", state = point.city)
                        createUI.FieldInCreateCard(fieldName = "Улица", state = point.street)
                        createUI.FieldInCreateCard(fieldName = "Дом", state = point.house)

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
                LoadUnloadButtons(vm)

                createUI.CreateButton { vm.createTrip() }

            }

        }

    }

    @Composable
    fun LoadUnloadButtons(vm: CreateTripViewModel) {
        val points = vm.pointViews
        Row(modifier = Modifier.padding(vertical = 10.dp)) {
            Button(modifier = Modifier.padding(horizontal = 10.dp), onClick = {
                points.add(vm.createPointView("UPLOAD"))
            }) {
                Text("Загрузиться")
            }
            if (points.size > 0) {
                Button(modifier = Modifier.padding(horizontal = 10.dp), onClick = {
                    points.add(vm.createPointView("UNLOAD"))
                }) {
                    Text("Разгрузиться")
                }
            }
        }
    }


}


