package com.example.dispidition.presentation.screens.person

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.person.PersonsRegistryViewModel
import com.example.dispidition.R

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
            ) {
                item() {
                    Column {
                        for (person in persons){
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
            //TODO в отдельный
            Box(contentAlignment = Alignment.BottomEnd) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(modifier = Modifier
                        .padding(end = 20.dp, bottom = 15.dp)
                        .size(50.dp), onClick = {navController.navigate("createPerson")}) {
                        Icon(
                            painter = painterResource(R.drawable.addicon),
                            contentDescription = "добавить",
                            modifier = Modifier.size(60.dp).background(Color.White, shape = CircleShape).border(4.dp, Color.White, CircleShape),
                            tint = Color.Green
                        )
                    }
                }
            }
        }
    }
}