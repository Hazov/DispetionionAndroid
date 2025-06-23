package com.example.domain.usecase.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetLocationInRoom(appContext:Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val database by lazy { LocationDatabase.getInstance(applicationContext) }
    private val dao by lazy { database.locationDao() }

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
            val lastLocationTask = fusedLocationClient.lastLocation
            val result = lastLocationTask.await()

            if (result != null) {
                val location = LocationEntity(null, result.latitude, result.longitude, System.currentTimeMillis())
                dao.insert(location)
            }
        }
        return Result.success()
    }
}