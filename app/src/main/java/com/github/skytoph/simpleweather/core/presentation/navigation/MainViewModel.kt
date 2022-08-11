package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.MessageCommunication
import com.github.skytoph.simpleweather.core.presentation.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.Visibility
import com.github.skytoph.simpleweather.presentation.main.MainNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: MainNavigator,
    private val progressCommunication: ProgressCommunication.Observe,
    private val messageCommunication: MessageCommunication.Observe,
) : ViewModel() {

    init {
        navigator.showMain(R.id.fragment_container)
    }

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<ShowScreen>) =
        navigator.observe(owner, observer)

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Visibility>) =
        progressCommunication.observe(owner, observer)

    fun observeMessages(owner: LifecycleOwner, observer: Observer<String>) {
        messageCommunication.observe(owner, observer)
    }

}