package com.example.dispidition.presentation.screens.trip


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.dispidition.app.global.GlobalSettings
import com.example.dispidition.presentation.viewmodel.trip.trip_gps.TripGpsViewModel
import com.example.domain.model.gps.Location
import com.example.ui.details.DetailsUI
import com.example.ui.entites.trip.TripUI
import java.time.format.DateTimeFormatter



class TripGPSReportScreen(
    val detailsUI: DetailsUI,
    val globalSettings: GlobalSettings,
    val navController: NavHostController,
) {
    var formatter = DateTimeFormatter.ofPattern("dd.MM")

    @Composable
    fun Init(id: Long?, vm: TripGpsViewModel = hiltViewModel()) {
        if (!globalSettings.authenticated.value) {
            navController.navigate("login")
        }
        if (id != null) {
            vm.fetchTripGps(id)
            Show(vm);
        }
    }

    @Composable
    fun Show(vm: TripGpsViewModel) {


    }

    @Composable
    fun LocationItem(location: Location) {
        Column(modifier = Modifier.padding(8.dp)) {
            // Загрузка статической карты через AsyncImage
            AsyncImage(
                model = buildStaticYMapsUrl(location.latitude, location.longitude),
                contentDescription = "Карта",
                modifier = Modifier.size(200.dp)
            )

            // Название места и координаты
            Text("${location.latitude}, ${location.longitude}")

            Spacer(modifier = Modifier.height(8.dp))

            // Кнопка для открытия в полномасштабной карте
            Button(onClick = { /* тут добавляем логику открытия полного приложения карты */ }) {
                Text("Открыть на карте")
            }
        }
    }

    // Вспомогательная функция для формирования URL статичной карты
    private fun buildStaticYMapsUrl(lat: Double, lon: Double): String {
        return "https://static-maps.yandex.ru/1.x/" +
                "?apikey=YOUR_API_KEY" + // твой ключ API
                "&l=map" +
                "&size=400,400" +
                "&pt=${lon},${lat},pm2rdm"
    }


}