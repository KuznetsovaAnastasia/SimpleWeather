package com.github.skytoph.simpleweather.presentation.main

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowScreen

interface MainNavigator {
    fun showMain(@IdRes container: Int)
    fun observe(owner: LifecycleOwner, observer: Observer<ShowScreen>)
}
