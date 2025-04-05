package com.example.dispidition.presentation.screens.truck

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.truck.CreateTruckViewModel

class CreateTruckScreen(val navController: NavHostController) {

    @Composable
    fun Init(vm: CreateTruckViewModel = hiltViewModel()) {
        vm.newTruckModel()
        Show(vm)
    }

    @Composable
    fun Show(vm: CreateTruckViewModel) {
        val truck = vm.truck
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            //Заголовок Создание авто
            Row(modifier = Modifier.fillMaxWidth().background(color = Color.White), horizontalArrangement = Arrangement.Center) {
                Text(modifier = Modifier.padding(15.dp), text = "Создание авто", fontSize = 30.sp)
            }

            Spacer(Modifier.padding(vertical = 10.dp))

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
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        Text(modifier = Modifier.padding(bottom = 5.dp), text = "Марка")
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = truck.brand.value,
                            onValueChange = { truck.brand.value = it },
                            label = { Text(text = "Марка") })
                    }
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        Text(modifier = Modifier.padding(bottom = 5.dp), text = "Модель")
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = truck.model.value,
                            onValueChange = { truck.model.value = it },
                            label = { Text(text = "Модель") })
                    }
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        Text(modifier = Modifier.padding(bottom = 5.dp), text = "Номер")
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = truck.roadNumber.value,
                            onValueChange = { truck.roadNumber.value = it },
                            label = { Text(text = "Номер") })
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = {vm.createTruck()}) {
                    Text("Создать")
                }
            }


        }
    }

}