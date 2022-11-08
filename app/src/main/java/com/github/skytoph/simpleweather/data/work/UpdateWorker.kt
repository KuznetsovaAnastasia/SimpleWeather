package com.github.skytoph.simpleweather.data.work

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.github.skytoph.simpleweather.domain.work.UpdateForecastInteractor
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

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.e(this::class.simpleName, "doWork: start working")
        try {
            interactor.updateForecasts()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}