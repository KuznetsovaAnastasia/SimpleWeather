package com.github.skytoph.simpleweather.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class UpdateWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val interactor: UpdateForecastInteractor,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = try {
        withContext(Dispatchers.IO) {
            interactor.updateForecasts()
            Result.success()
        }
    } catch (e: Exception) {
        Log.e(TAG, e.stackTraceToString())
        Result.failure()
    }

    private companion object {
        const val TAG = "ErrorTag: Worker"
    }
}