package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import javax.inject.Inject

interface DailyForecastDataMapper : Mapper<DailyForecastData> {

    fun map(
        time: Long,
        tempMin: Double,
        tempMax: Double,
        weatherId: Int,
        pop: Double,
    ): DailyForecastData

    fun map(
        time: Long,
        temp: Pair<Double, Double>,
        weatherId: Int,
        pop: Double,
    ): DailyForecastData

    class Base @Inject constructor() : DailyForecastDataMapper {

        override fun map(
            time: Long,
            tempMin: Double,
            tempMax: Double,
            weatherId: Int,
            pop: Double,
        ) = DailyForecastData(time, Pair(tempMin, tempMax), weatherId, pop)

        override fun map(
            time: Long,
            temp: Pair<Double, Double>,
            weatherId: Int,
            pop: Double,
        ) = DailyForecastData(time, temp, weatherId, pop)
    }
}