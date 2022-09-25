package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.domain.mapper.WeatherIdHandler
import com.github.skytoph.simpleweather.core.util.formatter.TemperatureFormatter
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent.Current
import javax.inject.Inject

interface CurrentWeatherDomainToUiMapper : Mapper<Current> {
    fun map(city: String, temperature: Double, weatherId: Int): Current

    class Base @Inject constructor(private val tempFormatter: TemperatureFormatter) :
        CurrentWeatherDomainToUiMapper, WeatherIdHandler() {

        override fun map(city: String, temperature: Double, weatherId: Int): Current =
            Current(city, tempFormatter.format(temperature), weatherImageRes(weatherId))
    }
}
