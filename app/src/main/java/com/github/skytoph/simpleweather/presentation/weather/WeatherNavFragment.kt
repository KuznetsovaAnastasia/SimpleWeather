package com.github.skytoph.simpleweather.presentation.weather

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.BaseFragment
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowStrategy

class WeatherNavFragment(
    @IdRes container: Int,
    private val id: String,
    private val favorite: Boolean = false,
) : NavigationScreen("WeatherFragment$id",
    WeatherFragment::class.java,
    container,
    ShowStrategy.REPLACE) {

    override fun fragment(): BaseFragment<*, *> = WeatherFragment.newInstance(id, favorite)
}