package com.github.skytoph.simpleweather.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.communication.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowScreen
import com.github.skytoph.simpleweather.domain.work.UpdateForecastWork
import com.github.skytoph.simpleweather.presentation.favorites.RefreshCommunication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: MainNavigator,
    private val progressCommunication: ProgressCommunication.Mutable,
    private val refreshCommunication: RefreshCommunication.Update,
    private val worker: UpdateForecastWork,
) : ViewModel() {

    fun showMain() = navigator.showMain(R.id.fragment_container)

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<ShowScreen>) =
        navigator.observe(owner, observer)

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Boolean>) =
        progressCommunication.observe(owner, observer)

    fun scheduleUpdateForecast(owner: LifecycleOwner) =
        worker.scheduleWork(owner) { info: WorkInfo ->
            when (info.state) {
                WorkInfo.State.RUNNING -> progressCommunication.show(true)
                WorkInfo.State.SUCCEEDED, WorkInfo.State.ENQUEUED -> {
                    progressCommunication.show(false)
                    refreshCommunication.show(true)
                }
            }
        }
}