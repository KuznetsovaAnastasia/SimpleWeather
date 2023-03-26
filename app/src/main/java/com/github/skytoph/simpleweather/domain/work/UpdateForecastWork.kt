package com.github.skytoph.simpleweather.domain.work

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.work.*
import com.github.skytoph.simpleweather.data.work.UpdateWorker
import java.util.concurrent.TimeUnit

interface UpdateForecastWork {
    fun scheduleWork(owner: LifecycleOwner, observer: Observer<List<WorkInfo>>)

    class Base(private val workManager: WorkManager) : UpdateForecastWork {

        override fun scheduleWork(owner: LifecycleOwner, observer: Observer<List<WorkInfo>>) {
            val constraints =
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            val request =
                PeriodicWorkRequestBuilder<UpdateWorker>(6, TimeUnit.HOURS, 30, TimeUnit.MINUTES)
                    .setConstraints(constraints)
                    .build()
            workManager.enqueueUniquePeriodicWork(WORK_NAME,
                ExistingPeriodicWorkPolicy.REPLACE,
                request)
            workManager.getWorkInfosForUniqueWorkLiveData(WORK_NAME).observe(owner, observer)
        }

        private companion object {
            const val WORK_NAME = "update_weather_forecasts_work"
        }
    }
}
