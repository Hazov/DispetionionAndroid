package com.example.dispidition.app.firebase

import android.annotation.SuppressLint
import com.google.android.gms.location.LocationServices
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.isNotEmpty()) {
            val data = remoteMessage.data["action"]
            when(data){
                "send_gps_data" -> sendCurrentLocation()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun sendCurrentLocation(){
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if(location != null){
                val lat = location.latitude
                val lon = location.longitude

                // Здесь реализуйте отправку полученных координат на сервер
                sendLocationToServer(lat, lon)
            }
        }.addOnFailureListener{

        }
    }

    private fun sendLocationToServer(latitude:Double, longitude:Double){
        // Ваш код отправки данных на сервер
    }
}