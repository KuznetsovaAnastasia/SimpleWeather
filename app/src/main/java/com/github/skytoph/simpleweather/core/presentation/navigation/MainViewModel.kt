package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.Visibility
import com.github.skytoph.simpleweather.presentation.main.MainNavFragment

class MainViewModel(
    private val navCommunication: NavigationCommunication.Mutable,
    private val progressCommunication: ProgressCommunication.Observe
) : ViewModel() {

    init {
        navCommunication.show(MainNavFragment(R.id.fragment_container))
    }

    fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationScreen>) =
        navCommunication.observe(owner, observer)

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Visibility>) =
        progressCommunication.observe(owner, observer)

}