package com.github.skytoph.simpleweather.presentation.weather

import androidx.annotation.IdRes

interface NavigateToWeather {
    fun showWeather(@IdRes container: Int, id: String, favorite: Boolean)
}