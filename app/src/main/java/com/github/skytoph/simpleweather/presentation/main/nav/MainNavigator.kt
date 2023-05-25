package com.github.skytoph.simpleweather.presentation.main.nav

import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowScreen

interface MainNavigator {
    fun showMain(@IdRes container: Int)
    fun observe(owner: LifecycleOwner, observer: Observer<ShowScreen>)
}
