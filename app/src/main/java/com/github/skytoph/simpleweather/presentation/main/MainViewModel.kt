package com.github.skytoph.simpleweather.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.communication.MessageCommunication
import com.github.skytoph.simpleweather.core.presentation.communication.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.error.UiMessage
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: MainNavigator,
    private val progressCommunication: ProgressCommunication.Observe,
) : ViewModel() {

    fun showMain() = navigator.showMain(R.id.fragment_container)

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<ShowScreen>) =
        navigator.observe(owner, observer)

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Boolean>) =
        progressCommunication.observe(owner, observer)
}