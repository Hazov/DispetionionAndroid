package com.example.dispidition.presentation.screens.trip

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dispidition.presentation.viewmodel.trip.TripRegistryViewModel
import com.example.dispidition.presentation.viewmodel.truck.TrucksRegistryViewModel

class TripsRegistryScreen(val navController: NavHostController) {

    @Composable
    fun Init(vm: TripRegistryViewModel = hiltViewModel()) {
        vm.fetchTrucks()
        Show(vm);
    }

    @Composable
    fun Show(vm: TripRegistryViewModel) {

        val trips = vm.trips.observeAsState().value
        if(trips != null){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(trips) { trip ->
                    val stateColor = remember {
                        mutableStateOf(Color.Black)
                    }
                    if (trip.equals("Активна")) {
                        stateColor.value = Color(0xff57b44e);
                    } else {
                        stateColor.value = Color(0xffb2b2b2);

                    }
                    Card(modifier = Modifier
                        .padding(15.dp)
                        .defaultMinSize(minHeight = 110.dp)
                        .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            15.dp
                        ),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        onClick = {navController.navigate("trip/${trip.id}")}) {
                        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
                            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                Row {
                                    Text(text = trip.sourceAddress?.city.orEmpty(), fontSize = 17.sp)
                                }
                                Row {
                                    Text(text = trip.destinationAddress?.city.orEmpty(), fontSize = 17.sp)
                                }
                            }
                            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                Row {
                                    Text(text = "Грузоперевозчик: " + trip.truck?.roadNumber.orEmpty(), fontSize = 12.sp)

                                }
                                Row {
                                    Text(text = "(" + trip + "  " + trip + ")", fontSize = 9.sp)
                                }

                            }


                        }
                    }

                }

            }
        }

    }
}