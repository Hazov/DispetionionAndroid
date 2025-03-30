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
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dispidition.presentation.viewmodel.truck.TruckDetailsViewModel


class TruckDetailsScreen(
    val navController: NavHostController,
) {

    @Composable
    fun Init(truckId: Long?, vm: TruckDetailsViewModel = hiltViewModel()) {
        if(truckId != null){
            vm.fetchTruck(truckId)
            Show(vm);
        }
    }


    @Composable
    fun Show(vm: TruckDetailsViewModel) {
        val truck = vm.truck.observeAsState().value

        Column(
            modifier = Modifier
                .fillMaxSize()
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
                    Text(text = "Информация по поездке")
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

                }
            }
        }


    }
}



