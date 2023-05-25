package com.github.skytoph.simpleweather.presentation.settings.nav

import androidx.annotation.IdRes

interface SettingsNavigator {
    fun showAbout(@IdRes container: Int)
}