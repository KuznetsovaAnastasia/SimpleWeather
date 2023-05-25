package com.github.skytoph.simpleweather.domain.work

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.work.*
import com.github.skytoph.simpleweather.data.work.UpdateWorker
import java.util.*
import java.util.concurrent.TimeUnit

interface UpdateForecastWork {
    fun scheduleWork()
    fun observeWork(owner: LifecycleOwner, observer: Observer<WorkInfo>)

    abstract class Abstract(protected val workManager: WorkManager) : UpdateForecastWork {
        protected lateinit var workId: UUID

        override fun observeWork(owner: LifecycleOwner, observer: Observer<WorkInfo>) {
            workManager.getWorkInfoByIdLiveData(workId).observe(owner, observer)
        }
    }

    class Periodically(workManager: WorkManager) : Abstract(workManager) {

        override fun scheduleWork() {
            val data =
                Data.Builder().putInt(UpdateWorker.ARG_CRITERIA, UpdateWorker.CRITERIA_DAY)
                    .putBoolean(UpdateWorker.ARG_RETRY, true).build()
            val request =
                PeriodicWorkRequestBuilder<UpdateWorker>(6, TimeUnit.HOURS, 30, TimeUnit.MINUTES)
                    .setConstraints(
                        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
                    )
                    .setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS
                    )
                    .setInputData(data)
                    .build()
            workId = request.id
            workManager.enqueueUniquePeriodicWork(
                WORK_NAME, ExistingPeriodicWorkPolicy.REPLACE, request
            )
        }

        private companion object {
            const val WORK_NAME = "update_weather_forecasts_periodically_work"
        }
    }

    class Once(workManager: WorkManager) : Abstract(workManager) {

        override fun scheduleWork() {
            val data =
                Data.Builder().putInt(UpdateWorker.ARG_CRITERIA, UpdateWorker.CRITERIA_HOUR)
                    .putBoolean(UpdateWorker.ARG_RETRY, false).build()
            val request = OneTimeWorkRequestBuilder<UpdateWorker>()
                .setConstraints(
                    Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
                )
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .setInputData(data)
                .build()
            workId = request.id
            workManager.enqueueUniqueWork(WORK_NAME, ExistingWorkPolicy.REPLACE, request)
        }

        private companion object {
            const val WORK_NAME = "update_weather_forecasts_once_work"
        }
    }
}
