package com.github.skytoph.simpleweather.core.domain.mapper

import com.github.skytoph.simpleweather.R

abstract class WeatherImageId {

    protected fun weatherImageRes(weatherId: Int, isDaytime: Boolean = true) = when {
        weatherId in 200..599 -> R.drawable.weather_rain
        weatherId in 600..699 -> R.drawable.weather_snow
        weatherId in 700..799  -> R.drawable.weather_mist
        weatherId in 800..801 && isDaytime -> R.drawable.weather_sun
        weatherId in 800..801 -> R.drawable.weather_moon
        weatherId == 802 && isDaytime -> R.drawable.weather_clouds_sun
        weatherId == 802 -> R.drawable.weather_clouds_moon
        weatherId in 803..804 -> R.drawable.weather_clouds
        !isDaytime -> R.drawable.weather_clouds_moon
        else -> R.drawable.weather_clouds_sun
    }
}