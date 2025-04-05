package com.example.dispidition.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

//        @RequiresApi(Build.VERSION_CODES.N)
//        fun requestPermissions() {
//            val locationPermissionRequest = registerForActivityResult(
//                ActivityResultContracts.RequestMultiplePermissions()
//            ) { permissions ->
//                when {
//                    permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
//                        // Precise location access granted.
//                    }
//                    else -> {
//                        // No location access granted.
//                    }
//                }
//            }
//            locationPermissionRequest.launch(
//                arrayOf(
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                )
//            )
//        }
//        requestPermissions()
//
//
//        val mlocManager = getSystemService(LOCATION_SERVICE) as LocationManager;
//        val mlocListener = MyLocationListener();
//        mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0f, mlocListener);






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
                topBar = {BottomNavBar(navController)}) { padding ->
                Image(
                    painter = painterResource(R.drawable.img_4),
                    "bg",
                    Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop


                )

                Column(Modifier.padding(padding)) {
                    NavHost(
                        navController = navController,
                        startDestination = "trucks",
                        modifier = Modifier.weight(1f)
                    ) {

                        composable("trucks") {
                            trucksRegistryScreen.Init()
                        }
                        composable("truck/{truckId}", arguments = listOf(navArgument("truckId") { type = NavType.LongType })) {
                                stackEntry ->
                            val userId = stackEntry.arguments?.getLong("truckId")
                            truckDetailsScreen.Init(userId)
                        }
                        composable("createTruck") {
                            createTruckScreen.Init()
                        }

                        composable("trips") {
                            tripsRegistryScreen.Init()
                        }

                        composable("trip/{tripId}", arguments = listOf(navArgument("tripId") { type = NavType.LongType })) {
                                stackEntry ->
                            val tripId = stackEntry.arguments?.getLong("tripId")
                            tripDetailsScreen.Init(tripId)
                        }

                        composable("createTrip") {
                            createTripScreen.Init()
                        }

                        composable("persons") {
                            personsRegistryScreen.Init()
                        }

                        composable("person/{personId}", arguments = listOf(navArgument("personId") { type = NavType.LongType })) {
                                stackEntry ->
                            val userId = stackEntry.arguments?.getLong("personId")
                            personDetailsScreen.Init(userId)
                        }
                        composable("createPerson") {
                            createPersonScreen.Init()
                        }
                    }
                }
            }

        }
    }

    @Composable
    fun BottomNavBar(navController: NavHostController) {
        NavigationBar(containerColor = Color.Transparent) {
            NavBarItems.BarItems.forEach { navItem ->
                NavigationBarItem(
                    selected = true,
                    onClick = {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
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



//
//class MyLocationListener: LocationListener {
//
//    var latitude = 0.0;
//    var longitude=0.0;
//
//    override fun onLocationChanged(loc: Location) {
//        latitude = loc.getLatitude();
//        longitude = loc.getLongitude();
//    }
//
//}


