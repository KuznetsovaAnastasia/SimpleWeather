package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.domain.mapper.WeatherIdHandler
import com.github.skytoph.simpleweather.core.util.formatter.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TemperatureFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent.HourlyForecast
import javax.inject.Inject


interface HourlyForecastToUiMapper : Mapper<HourlyForecast> {

    fun map(time: Long, temp: Double, weatherId: Int, pop: Double): HourlyForecast

    class Base @Inject constructor(
        private val tempFormatter: TemperatureFormatter,
        private val timeFormatter: TimeFormatter,
        private val probabilityFormatter: ProbabilityFormatter,
    ) : HourlyForecastToUiMapper, WeatherIdHandler() {

        override fun map(time: Long, temp: Double, weatherId: Int, pop: Double) =
            HourlyForecast(timeFormatter.timeFull(time),
                tempFormatter.format(temp),
                weatherImageRes(weatherId),
                probabilityFormatter.format(pop))
    }
}