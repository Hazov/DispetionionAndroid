package com.example.dispidition.presentation.screens.trip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


class CreateTripScreen(val navController: NavHostController) {
    @Composable
    fun Show() {
        val suggestions = listOf<Int>(5, 4, 3)
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 150.dp)
        ) {
            Row {
                Text(text = "Создание поездки")
            }

            //Авто (one of list)
            OutlinedTextField(
                "",
                {},
                leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "Проверено") },
                trailingIcon = {
                    Icon(
                        Icons.Filled.Info,
                        contentDescription = "Дополнительная информация"
                    )
                },
                label = { Text(text = "Авто") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )
            //Водитель (one of list)
            OutlinedTextField(
                "",
                {},
                leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "Проверено") },
                trailingIcon = {
                    Icon(
                        Icons.Filled.Info,
                        contentDescription = "Дополнительная информация"
                    )
                },
                label = { Text(text = "Водитель") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()

            )

            Row{
                Button(onClick = {}) {
                    Text(text = "Добавить загрузку")
                }
                Button(onClick = {}) {
                    Text(text = "Добавить разгрузку")
                }
            }


//                CheckBox()

//                OutlinedTextField(
//                    value = "",
//                    onValueChange = { },
//                    modifier = Modifier.fillMaxWidth(),
//                    label = { Text("Водитель") },
//                )
//                DropdownMenu(
//                    expanded = suggestions.isNotEmpty(),
//                    onDismissRequest = {  },
//                    modifier = Modifier.fillMaxWidth(),
//                    properties = PopupProperties(focusable = false)
//                ) {
//                    suggestions.forEach { label ->
//                        DropdownMenuItem(
//                            onClick = {},
//                            text = {
//                                Text(text = label.toString())
//                            }
//                        )
//                    }
//                }
        }

    }
    @Composable
    fun loadCard(isUpload: Boolean){
        Card{
           Row {
//               Text(text = isUpload ? "Загрузиться" : "Разрузиться")
           }
            Column {
                //Наименование груза
                OutlinedTextField(
                    "",
                    {},
                    leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "Наименование груза") },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = "Дополнительная информация"
                        )
                    },
                    label = { Text(text = "Откуда") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()

                )
                //Город (one of List)
                OutlinedTextField(
                    "",
                    {},
                    leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "Город") },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = "Дополнительная информация"
                        )
                    },
                    label = { Text(text = "Откуда") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()

                )
                //Улица
                OutlinedTextField(
                    "",
                    {},
                    leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "Улица") },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = "Дополнительная информация"
                        )
                    },
                    label = { Text(text = "Откуда") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()

                )
                //Дом
                OutlinedTextField(
                    "",
                    {},
                    leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "Дом") },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = "Дополнительная информация"
                        )
                    },
                    label = { Text(text = "Откуда") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()

                )
            }
        }
    }
}