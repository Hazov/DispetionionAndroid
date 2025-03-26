package com.example.dispidition.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dispidition.R
import com.example.dispidition.presentation.screens.person.CreatePersonScreen
import com.example.dispidition.presentation.screens.person.PersonDetailsScreen
import com.example.dispidition.presentation.screens.person.PersonsRegistryScreen
import com.example.dispidition.presentation.screens.trip.CreateTripScreen
import com.example.dispidition.presentation.screens.trip.TripDetailsScreen
import com.example.dispidition.presentation.screens.trip.TripsRegistryScreen
import com.example.dispidition.presentation.screens.truck.CreateTruckScreen
import com.example.dispidition.presentation.screens.truck.TruckDetailsScreen
import com.example.dispidition.presentation.screens.truck.TrucksRegistryScreen


class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val trucksRegistryScreen = TrucksRegistryScreen(navController)
            val truckDetailsScreen = TruckDetailsScreen(navController)
            val createTruckScreen = CreateTruckScreen(navController)

            val tripsRegistryScreen = TripsRegistryScreen(navController)
            val tripDetailsScreen = TripDetailsScreen(navController)
            val createTripScreen = CreateTripScreen(navController)

            val personsRegistryScreen = PersonsRegistryScreen(navController)
            val personDetailsScreen = PersonDetailsScreen(navController)
            val createPersonScreen = CreatePersonScreen(navController)

            Scaffold(Modifier.fillMaxSize(),
                bottomBar = {BottomNavBar(navController)},
                topBar = {BottomNavBar(navController)}) {

                Column {
                    NavHost(
                        navController = navController,
                        startDestination = "truck",
                        modifier = Modifier.weight(1f)
                    ) {

                        composable("trucks") {
                            trucksRegistryScreen.Show()
                        }
                        composable("truck") {
                            truckDetailsScreen.Init()
                        }
                        composable("createTruck") {
                            createTruckScreen.Show()
                        }

                        composable("trips") {
                            tripsRegistryScreen.Show()
                        }
                        composable("trip") {
                            tripDetailsScreen.Show()
                        }
                        composable("createTrip") {
                            createTripScreen.Show()
                        }

                        composable("persons") {
                            personsRegistryScreen.Show()
                        }
                        composable("person") {
                            personDetailsScreen.Show()
                        }
                        composable("createPerson") {
                            createPersonScreen.Show()
                        }
                    }
                }
            }

        }
    }

    @Composable
    fun BottomNavBar(navController: NavHostController) {
        NavigationBar(containerColor = Color.Black) {
            NavBarItems.BarItems.forEach { navItem ->
                NavigationBarItem(
                    selected = true,
                    onClick = {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {

                        Icon(
                            painter = painterResource(navItem.image),
                            contentDescription = navItem.title,
                            modifier = Modifier.size(40.dp)
                        )
                    },
                    label = {
                        Text(text = navItem.title)
                    },

                )
            }
        }
    }
}


object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Авто",
            image = R.drawable.carsicon,
            route = "trucks"
        ),
        BarItem(
            title = "Поездки",
            image = R.drawable.tripsicon,
            route = "trips"
        ),
        BarItem(
            title = "Персонал",
            image = R.drawable.personsicon,
            route = "persons"
        )
    )

}

data class BarItem(
    val title: String,
    val image: Int,
    val route: String
)



