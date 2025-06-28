package com.example.dispidition.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dispidition.R
import com.example.dispidition.presentation.screens.auth.LoginScreen
import com.example.dispidition.presentation.screens.person.CreatePersonScreen
import com.example.dispidition.presentation.screens.person.PersonDetailsScreen
import com.example.dispidition.presentation.screens.person.PersonsRegistryScreen
import com.example.dispidition.presentation.screens.trip.CreateTripScreen
import com.example.dispidition.presentation.screens.trip.TripDetailsScreen
import com.example.dispidition.presentation.screens.trip.TripsRegistryScreen
import com.example.dispidition.presentation.screens.trip.forDriver.TripRouteScreen
import com.example.dispidition.presentation.screens.truck.CreateTruckScreen
import com.example.dispidition.presentation.screens.truck.TruckDetailsScreen
import com.example.dispidition.presentation.screens.truck.TrucksRegistryScreen
import com.example.dispidition.app.global.GlobalSettings
import com.example.dispidition.presentation.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.Manifest
import android.annotation.SuppressLint
import com.example.dispidition.presentation.screens.trip.TripGPSReportScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var globalSettings: GlobalSettings

    @Inject
    lateinit var navController: NavHostController

    @Inject
    lateinit var createPersonScreen: CreatePersonScreen

    @Inject
    lateinit var trucksRegistryScreen: TrucksRegistryScreen

    @Inject
    lateinit var truckDetailsScreen: TruckDetailsScreen

    @Inject
    lateinit var createTruckScreen: CreateTruckScreen

    @Inject
    lateinit var tripsRegistryScreen: TripsRegistryScreen

    @Inject
    lateinit var tripDetailsScreen: TripDetailsScreen

    @Inject
    lateinit var createTripScreen: CreateTripScreen

    @Inject
    lateinit var driverTripRouteScreen: TripRouteScreen

    @Inject
    lateinit var personsRegistryScreen: PersonsRegistryScreen

    @Inject
    lateinit var personDetailsScreen: PersonDetailsScreen

    @Inject
    lateinit var loginScreen: LoginScreen

    @Inject
    lateinit var tripGpsReportScreen: TripGPSReportScreen

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestGpsPermissions()
        
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            if(globalSettings.accessFineLocation.value){
                Scaffold(Modifier.fillMaxSize(),
                    bottomBar = {
                        if (globalSettings.authenticated.value && globalSettings.permissions.contains("ADMIN")) {
                            BottomNavBar(navController)
                        }
                    },
                    topBar = {
                        TopNavBar(navController)
                    })
                { padding ->

                    Image(
                        painter = painterResource(R.drawable.img_4),
                        "bg",
                        Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Column(Modifier.padding(padding)) {
                        NavHost(
                            navController = navController,
                            startDestination = "login",
                            modifier = Modifier.weight(1f)
                        ) {
                            composable("trucks") {
                                trucksRegistryScreen.Init()
                            }
                            composable(
                                "truck/{truckId}",
                                arguments = listOf(navArgument("truckId") { type = NavType.LongType })
                            ) { stackEntry ->
                                val truckId = stackEntry.arguments?.getLong("truckId")
                                truckDetailsScreen.Init(truckId)
                            }
                            composable("createTruck") {
                                createTruckScreen.Init()
                            }

                            composable("trips") {
                                tripsRegistryScreen.Init()
                            }

                            composable(
                                "trip/{tripId}",
                                arguments = listOf(navArgument("tripId") { type = NavType.LongType })
                            ) { stackEntry ->
                                val tripId = stackEntry.arguments?.getLong("tripId")
                                tripDetailsScreen.Init(tripId)
                            }

                            composable("createTrip") {
                                createTripScreen.Init()
                            }

                            composable(
                                "tripGps/{tripId}",
                                arguments = listOf(navArgument("tripId") { type = NavType.LongType })
                            ) { stackEntry ->
                                val tripId = stackEntry.arguments?.getLong("tripId")
                                tripGpsReportScreen.Init(tripId)
                            }

                            composable("persons") {
                                personsRegistryScreen.Init()
                            }

                            composable(
                                "person/{personId}",
                                arguments = listOf(navArgument("personId") { type = NavType.LongType })
                            ) { stackEntry ->
                                val userId = stackEntry.arguments?.getLong("personId")
                                personDetailsScreen.Init(userId)
                            }
                            composable("createPerson") {
                                createPersonScreen.Init()
                            }
                            composable("login") {
                                loginScreen.Init()
                            }

                            composable("driver_tripRoute") {
                                driverTripRouteScreen.Init()
                            }


                        }
                    }
                }
            }


        }
    }

    @Composable
    fun TopNavBar(navController: NavHostController, vm: LoginViewModel = hiltViewModel()) {
//        if (vm.authenticated.value) {
        NavigationBar(containerColor = Color.Transparent) {
            Row(horizontalArrangement = Arrangement.End) {
                IconButton(
                    onClick = {
                        vm.logout(navController)
                    },
                    Modifier.size(30.dp, 30.dp)
                ) {
                    Icon(painterResource(R.drawable.profileicon), "Выход")
                }
            }
        }
//        }
    }

    @Composable
    fun BottomNavBar(navController: NavHostController, vm: LoginViewModel = hiltViewModel()) {
//        if (vm.authenticated.value && vm.permissions.contains("ADMIN")) {
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
//        }
    }

    fun requestGpsPermissions() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {

                    globalSettings.accessFineLocation.value = true
                }
                else -> {
                    globalSettings.accessFineLocation.value = false
                }
            }
        }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )
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






