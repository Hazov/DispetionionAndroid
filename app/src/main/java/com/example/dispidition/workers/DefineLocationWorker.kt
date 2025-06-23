package com.example.dispidition.workers

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.domain.repository.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// Определяет местоположение и пишет в Room. Из Room достает SendLocationWorker
class DefineLocationWorker @Inject constructor(
    appContext: Context,
    workerParams: WorkerParameters,
    private val locationRepository: LocationRepository
) : CoroutineWorker(appContext, workerParams) {


    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        try {

            locationRepository.defineLocation()
            if (location != null) {
                locationRepository.addLocation(location.latitude, location.longitude, "Auto")
            }

            return Result.success()
        } catch (exception: Exception) {
            // В случае ошибки возвращаем Result.retry() для повтора задачи позднее
            return Result.retry()
        }
    }
}