package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.presentation.view.horizon.ResourceProvider
import com.github.skytoph.simpleweather.core.util.formatter.TemperatureFormatter
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent.Current
import javax.inject.Inject

interface CurrentWeatherDomainToUiMapper : Mapper<Current> {
    fun map(city: String, temperature: Double, weatherId: Int): Current

    class Base @Inject constructor(
        private val tempFormatter: TemperatureFormatter,
        private val resourceProvider: ResourceProvider,
    ) : CurrentWeatherDomainToUiMapper {

        override fun map(city: String, temperature: Double, weatherId: Int): Current {
            val image = when (weatherId) {
                in 200..599 -> R.drawable.weather_rain
                in 600..699 -> R.drawable.weather_snow
                in 800..801 -> R.drawable.weather_sun
                802 -> R.drawable.weather_clouds_sun
                in 803..804 -> R.drawable.weather_clouds
                in 700..799 -> R.drawable.weather_clouds
                else -> R.drawable.weather_clouds_sun
            }
            val temp = resourceProvider.string(R.string.degrees_celsius,
                tempFormatter.format(temperature))
            return Current(city, temp, image)
        }
    }
}
