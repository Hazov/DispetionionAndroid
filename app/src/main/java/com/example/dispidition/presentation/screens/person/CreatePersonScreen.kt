package com.example.dispidition.presentation.screens.person

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
import com.example.dispidition.presentation.viewmodel.person.CreatePersonViewModel

class CreatePersonScreen(val navController:NavHostController) {
    @Composable
    fun Init(vm: CreatePersonViewModel = hiltViewModel()) {
        vm.newPerson()
        Show(vm)
    }

    @Composable
    fun Show(vm: CreatePersonViewModel) {
        val person = vm.person
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            //Заголовок Создание авто
            Row(modifier = Modifier.fillMaxWidth().background(color = Color.White), horizontalArrangement = Arrangement.Center) {
                Text(modifier = Modifier.padding(15.dp), text = "Создание пользователя", fontSize = 30.sp)
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
                        Text(modifier = Modifier.padding(bottom = 5.dp), text = "Имя")
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = person.firstName.value,
                            onValueChange = { person.firstName.value = it },
                            label = { Text(text = "Имя") })
                    }
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        Text(modifier = Modifier.padding(bottom = 5.dp), text = "Фамилия")
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = person.lastName.value,
                            onValueChange = { person.lastName.value = it },
                            label = { Text(text = "Фамилия") })
                    }
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        Text(modifier = Modifier.padding(bottom = 5.dp), text = "Отчество")
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = person.middleName.value,
                            onValueChange = { person.middleName.value = it },
                            label = { Text(text = "Отчество") })
                    }

                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        Text(modifier = Modifier.padding(bottom = 5.dp), text = "Email")
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = person.email.value,
                            onValueChange = { person.email.value = it },
                            label = { Text(text = "Email") })
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = {vm.createPerson()}) {
                    Text("Создать")
                }
            }




        }

    }
}