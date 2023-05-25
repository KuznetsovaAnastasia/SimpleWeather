package com.github.skytoph.simpleweather.presentation.main.nav

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowStrategy
import com.github.skytoph.simpleweather.presentation.main.MainFragment


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