package com.example.dispidition.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.domain.repository.LocationRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
// Достает из Room и отправляет на сервер все локации , которые записал DefineLocationWorker
class SendLocationWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: LocationRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        try {
            val unsentLocations = repository.getUnsentLocations()
            if (unsentLocations.isNotEmpty()) {
                for (location in unsentLocations) {
                    repository.sendLocation(location)
                    //TODO remove из базы
                }
            }
            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.retry()
        }
    }



    companion object {
        fun createOneTimeWorkRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<SendLocationWorker>().build()
        }
    }
}