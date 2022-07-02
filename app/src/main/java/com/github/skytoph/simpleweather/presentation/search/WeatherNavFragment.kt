package com.github.skytoph.simpleweather.presentation.search

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.presentation.weatherlist.WeatherListFragment
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen

class WeatherNavFragment(@IdRes private val container: Int) :
    NavigationScreen("WeatherNavigationFragment", WeatherListFragment::class.java, container)