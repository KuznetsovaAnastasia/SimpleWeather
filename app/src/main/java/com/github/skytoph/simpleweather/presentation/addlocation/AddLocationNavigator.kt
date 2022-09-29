package com.github.skytoph.simpleweather.presentation.addlocation

import com.github.skytoph.simpleweather.core.presentation.navigation.NavigateBack
import com.github.skytoph.simpleweather.presentation.main.MainContentNavigator
import com.github.skytoph.simpleweather.presentation.weather.NavigateToWeather

interface AddLocationNavigator : NavigateToWeather, MainContentNavigator, NavigateBack