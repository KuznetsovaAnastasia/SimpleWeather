package com.github.skytoph.simpleweather.presentation.main

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowStrategy


class MainNavFragment(@IdRes private val container: Int, strategy: ShowStrategy=ShowStrategy.Add) :
    NavigationScreen(
        TAG,
        MainFragment::class.java,
        container,
        strategy
    ) {

    companion object {
        const val TAG = "MainNavigationScreen"
    }
}