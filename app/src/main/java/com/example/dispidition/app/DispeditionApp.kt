package com.example.dispidition.app

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.dispidition.workers.DefineLocationWorker
import com.example.dispidition.workers.SendLocationWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit


@HiltAndroidApp
class DispeditionApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeWorkers()
    }

    /**
     * Настройка рабочих процессов:
     */
    private fun initializeWorkers() {
        val backgroundWorkRequest = PeriodicWorkRequestBuilder<DefineLocationWorker>(10, TimeUnit.MINUTES)
            .build()

        val sendWorkRequest = PeriodicWorkRequestBuilder<SendLocationWorker>(1, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "background_gps_collection",
            ExistingPeriodicWorkPolicy.REPLACE,
            backgroundWorkRequest
        )

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "send_undelivered_data",
            ExistingPeriodicWorkPolicy.REPLACE,
            sendWorkRequest
        )
    }
}
