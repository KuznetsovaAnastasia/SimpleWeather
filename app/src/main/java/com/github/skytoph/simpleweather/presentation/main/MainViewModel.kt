package com.github.skytoph.simpleweather.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.communication.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowScreen
import com.github.skytoph.simpleweather.data.worker.UpdateWorker
import com.github.skytoph.simpleweather.presentation.favorites.RefreshCommunication
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: MainNavigator,
    private val progressCommunication: ProgressCommunication.Mutable,
    private val refreshCommunication: RefreshCommunication.Update,
) : ViewModel() {

    fun showMain() = navigator.showMain(R.id.fragment_container)

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<ShowScreen>) =
        navigator.observe(owner, observer)

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Boolean>) =
        progressCommunication.observe(owner, observer)

    fun scheduleUpdateForecast(workManager: WorkManager, owner: LifecycleOwner) {
        progressCommunication.show(true)
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val request = PeriodicWorkRequestBuilder<UpdateWorker>(12, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
        workManager.enqueueUniquePeriodicWork(WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, request)
        workManager.getWorkInfoByIdLiveData(request.id).observe(owner) { info: WorkInfo ->
            when (info.state) {
                WorkInfo.State.FAILED, WorkInfo.State.CANCELLED -> progressCommunication.show(false)
                WorkInfo.State.SUCCEEDED -> {
                    progressCommunication.show(false)
                    refreshCommunication.show(true)
                }
            }
        }
    }

    private companion object {
        const val WORK_NAME = "update_forecasts"
    }
}