package com.github.skytoph.simpleweather.presentation.settings.nav

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigateBack

interface SettingsNavigator : NavigateBack {
    fun showAbout(@IdRes container: Int)
}