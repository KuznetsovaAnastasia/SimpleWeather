package com.github.skytoph.simpleweather.domain.mapper

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.TemperatureFormatter
import com.github.skytoph.simpleweather.presentation.WeatherUiComponent
import com.github.skytoph.simpleweather.presentation.WeatherUiComponent.Current
import kotlin.math.roundToInt

interface CurrentWeatherDomainToUiMapper : Mapper<Current> {
    fun map(city: String, temperature: Double, weatherId: Int): Current

    class Base(private val tempFormatter: TemperatureFormatter) : CurrentWeatherDomainToUiMapper {

        override fun map(city: String, temperature: Double, weatherId: Int): Current {
            val image = when (weatherId) {
                in 200..599 -> R.drawable.weather_rain
//                in 600..699 -> R.drawable.weather_snow // todo make snow icon
                in 800..801 -> R.drawable.weather_sun
                802 -> R.drawable.weather_clouds_sun
                in 803..804 -> R.drawable.weather_clouds
                in 700..799 -> R.drawable.weather_clouds
                else -> R.drawable.weather_clouds_sun
            }
            return Current(city, tempFormatter.format(temperature), image)
        }
    }
}
