package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.HourlyForecastData
import javax.inject.Inject

interface HourlyForecastListDataMapper : Mapper<List<HourlyForecastData>> {

    fun <T : Mappable<HourlyForecastData, HourlyForecastDataMapper>> map(
        forecasts: List<T>,
        timezoneOffset: Int = 0,
    ): List<HourlyForecastData>

    class Base @Inject constructor() : HourlyForecastListDataMapper {

        override fun <T : Mappable<HourlyForecastData, HourlyForecastDataMapper>> map(
            forecasts: List<T>,
            timezoneOffset: Int,
        ): List<HourlyForecastData> {
            val mapper = object : HourlyForecastDataMapper {
                override fun map(
                    time: Long,
                    temp: Double,
                    weatherId: Int,
                    pop: Double,
                ) = HourlyForecastData(time + timezoneOffset, temp, weatherId, pop)
            }
            return forecasts.map { it.map(mapper) }
        }
    }
}
