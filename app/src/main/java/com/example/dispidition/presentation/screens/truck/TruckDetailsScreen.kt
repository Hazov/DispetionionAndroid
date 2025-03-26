package com.example.dispidition.presentation.screens.truck

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.truck_details.TruckDetailsViewModel
import com.example.dispidition.presentation.viewmodel.truck_details.TruckDetailsViewModelFactory


class TruckDetailsScreen(
    val navController: NavHostController,
) : ViewModelStore(){

    private lateinit var vm: TruckDetailsViewModel

    @Composable
    fun Init() {
        vm = ViewModelProvider(
            this,
            TruckDetailsViewModelFactory()
        )[TruckDetailsViewModel::class.java]

        vm.fetchTruck()
        Show();
    }


    @Composable
    fun Show() {
        val truck  = vm.truck.observeAsState().value
        Column(
            modifier = Modifier
                .fillMaxSize().padding(top = 150.dp)
        ) {
            Row {
                Text(text = "Общая информация")
            }
            Column {
                Card(
                    modifier = Modifier
                        .padding(15.dp)
                        .defaultMinSize(minHeight = 110.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        3.dp
                    ),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column {
                        Row {
                            Text(text = "Марка")
                            Text(text = truck?.brand ?: "")
                        }
                        Row {
                            Text(text = "Модель")
                            Text(text = truck?.model ?: "")
                        }
                        Row {
                            Text(text = "Номер")
                            Text(text = truck?.roadNumber ?: "")
                        }
                        Row {
                            Text(text = "Владелец")
                            Text(text = truck?.ownerName ?: "")
                        }
                    }
                }
                Row {
                    Text(text = "Информация по поездка")
                }
                Card(
                    modifier = Modifier
                        .padding(15.dp)
                        .defaultMinSize(minHeight = 110.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        3.dp
                    ),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
//                    Column {
//                        Row {
//                            Text(text = "Отправление")
//                            Text(text = truck.fromAddress ?: "")
//                        }
//                        Row {
//                            Text(text = "Назначение")
//                            Text(text = truck.toAddress ?: "")
//                        }
//                        Row {
//                            Text(text = "Водитель")
//                            Text(text = truck.driverName ?: "")
//                        }
//                        Row {
//                            Text(text = "Статус")
//                            Text(text = truck.status ?: "")
//                        }
//                    }
                }
            }
        }


    }
}



