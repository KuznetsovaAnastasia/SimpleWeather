package com.github.skytoph.simpleweather.presentation.settings

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowStrategy

class SettingsNavFragment(@IdRes private val container: Int) :
    NavigationScreen("SettingsNavigationScreen",
        SettingsFragment::class.java,
        container,
        ShowStrategy.Replace)