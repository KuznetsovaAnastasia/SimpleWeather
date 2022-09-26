package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.domain.mapper.WeatherIdHandler
import com.github.skytoph.simpleweather.core.util.formatter.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TemperatureFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent
import javax.inject.Inject

interface DailyForecastToUiMapper : Mapper<WeatherUiComponent.DailyForecast> {

    fun map(time: Long, temp: Pair<Double, Double>, weatherId: Int, pop: Double): WeatherUiComponent.DailyForecast

    class Base @Inject constructor(
        private val tempFormatter: TemperatureFormatter,
        private val timeFormatter: TimeFormatter,
        private val probabilityFormatter: ProbabilityFormatter,
    ) : DailyForecastToUiMapper, WeatherIdHandler() {

        override fun map(time: Long, temp: Pair<Double, Double>, weatherId: Int, pop: Double) =
            WeatherUiComponent.DailyForecast(timeFormatter.dayInWeek(time),
                tempFormatter.format(temp.second),
                tempFormatter.format(temp.first),
                weatherImageRes(weatherId),
                probabilityFormatter.format(pop))
    }
}