package com.github.skytoph.simpleweather.presentation.weather.nav

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface NavigateToWeather {
    fun showWeather(
        fragmentManager: FragmentManager,
        @IdRes container: Int,
        id: String,
        favorite: Boolean
    )
}