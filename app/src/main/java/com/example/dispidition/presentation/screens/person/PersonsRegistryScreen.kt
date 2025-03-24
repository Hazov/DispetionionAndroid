package com.example.dispidition.presentation.screens.person

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

class PersonsRegistryScreen(val navController: NavHostController) {
    @Composable
    fun Show() {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp)
        ) {
            items(
                listOf(
                    Person(
                        "Павел",
                        "Покашов",
                        "Водитель"

                    ),
                    Person(
                        "Кушпат",
                        "Садыров",
                        "Водитель"

                    ),
                    Person(
                        "Сидоров",
                        "Олег",
                        "Водитель",
                    ),
                )
            ) { person ->
                Card(modifier = Modifier
                    .padding(15.dp)
                    .defaultMinSize(minHeight = 60.dp)
                    .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        15.dp
                    ),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    onClick = { navController.navigate("person") }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row {
                            Text(text = person.lastName + " " + person.firstName)
                        }
                        Row {
                            Text(text = person.position)
                        }
                    }
                }
            }
        }
    }

}


data class Person(
    val firstName: String,
    val lastName: String,
    val position: String
)