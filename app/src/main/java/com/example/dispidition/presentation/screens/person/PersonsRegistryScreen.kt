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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.person.PersonsRegistryViewModel

class PersonsRegistryScreen(val navController: NavHostController) : Fragment() {

    @Composable
    fun Init(vm: PersonsRegistryViewModel = hiltViewModel()) {
        vm.fetchPersons()
        Show(vm);
    }


    @Composable
    fun Show(vm: PersonsRegistryViewModel) {
        val persons = vm.persons.observeAsState().value

        if (persons != null) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 150.dp)
            ) {
                items(persons) { person ->
                    Card(modifier = Modifier
                        .padding(15.dp)
                        .defaultMinSize(minHeight = 60.dp)
                        .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            15.dp
                        ),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        onClick = { navController.navigate("person/${person.id}") }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Row {
                                Text(text = person.lastName.orEmpty() + " " + person.firstName.orEmpty())
                            }
                            Row {
                                Text(text = person.position.orEmpty())
                            }
                        }

                    }
                }
            }
        }
    }
}