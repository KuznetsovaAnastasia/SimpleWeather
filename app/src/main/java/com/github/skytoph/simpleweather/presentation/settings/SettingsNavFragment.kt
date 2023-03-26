package com.github.skytoph.simpleweather.presentation.settings

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowStrategy

class SettingsNavFragment(
    @IdRes private val container: Int,
    strategy: ShowStrategy =ShowStrategy.Replace,
) : NavigationScreen(
    TAG,
    SettingsFragment::class.java,
    container,
    strategy
) {

    companion object {
        const val TAG = "SettingsNavigationScreen"
    }
}