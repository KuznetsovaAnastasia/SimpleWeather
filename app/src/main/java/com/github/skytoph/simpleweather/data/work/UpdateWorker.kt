package com.github.skytoph.simpleweather.data.work

import android.content.Context
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
    private val notification: LoadingNotification,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        setForeground(notification.createForegroundInfo())
        return withContext(Dispatchers.IO) {
            return@withContext try {
                interactor.updateForecasts()
                notification.cancel()
                Result.success()
            } catch (e: Exception) {
                notification.cancel()
                Result.failure()
            }
        }
    }
}