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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dispidition.data.repo.TruckRepositoryImpl
import com.example.dispidition.domain.repository.TruckRepository
import com.example.dispidition.domain.usecase.GetTruckUseCase


class TruckDetailsScreen(val navController: NavHostController) {

    //TODO by lazy
    val truckRepository = TruckRepositoryImpl();
    val getTruckUseCase = GetTruckUseCase(truckRepository);

    @Composable
    fun Init(){
        val truck = getTruckUseCase.execute();
        Show(truck);
    }


    @Composable
    fun Show(truck: TruckDetails, modifier: Modifier = Modifier) {


        Column(modifier = Modifier
                .fillMaxSize(),
        ) {
            Row {
                Text(text = "Общая информация")
            }
            Column {
                Card(modifier = Modifier
                    .padding(15.dp)
                    .defaultMinSize(minHeight = 110.dp)
                    .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        3.dp
                    ),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Column {
                        Row {
                            Text(text = "Марка")
                            Text(text = truck.mark)
                        }
                        Row {
                            Text(text = "Модель")
                            Text(text = truck.model)
                        }
                        Row {
                            Text(text = "Номер")
                            Text(text = truck.roadNumber)
                        }
                        Row {
                            Text(text = "Владелец")
                            Text(text = truck.ownerName)
                        }
                    }
                }
                Row {
                    Text(text = "Информация по поездка")
                }
                Card(modifier = Modifier
                    .padding(15.dp)
                    .defaultMinSize(minHeight = 110.dp)
                    .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        3.dp
                    ),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Column {
                        Row {
                            Text(text = "Отправление")
                            Text(text = truck.trip.fromAddress)
                        }
                        Row {
                            Text(text = "Назначение")
                            Text(text = truck.trip.toAddress)
                        }
                        Row {
                            Text(text = "Водитель")
                            Text(text = truck.trip.driverName)
                        }
                        Row {
                            Text(text = "Статус")
                            Text(text = truck.trip.status)
                        }
                    }
                }
            }
        }


    }
}


data class TruckDetails(
    val mark: String,
    val model: String,
    val roadNumber: String,
    val ownerName: String,
    val trip: TruckDetailsTrip
)


data class TruckDetailsTrip(
    val fromAddress: String,
    val toAddress: String,
    val driverName: String,
    val status: String,
)