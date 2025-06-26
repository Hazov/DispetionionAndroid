package com.example.dispidition.workers

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.domain.repository.LocationRepository
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

            val location = locationRepository.defineLocation()
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