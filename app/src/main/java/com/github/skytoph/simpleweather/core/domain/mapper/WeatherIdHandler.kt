package com.github.skytoph.simpleweather.core.domain.mapper

import com.github.skytoph.simpleweather.R

abstract class WeatherIdHandler {

    protected fun weatherImageRes(weatherId: Int) = when (weatherId) {
        in 200..599 -> R.drawable.weather_rain
        in 600..699 -> R.drawable.weather_snow
        in 800..801 -> R.drawable.weather_sun
        802 -> R.drawable.weather_clouds_sun
        in 803..804 -> R.drawable.weather_clouds
        in 700..799 -> R.drawable.weather_clouds
        else -> R.drawable.weather_clouds_sun
    }
}