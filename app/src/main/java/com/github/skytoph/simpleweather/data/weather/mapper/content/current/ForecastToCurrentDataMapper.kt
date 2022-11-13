package com.github.skytoph.simpleweather.data.weather.mapper.content.current

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.MappableToCurrent
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData
import javax.inject.Inject

interface ForecastToCurrentDataMapper : Mapper<CurrentWeatherData> {

    fun map(forecast: MappableToCurrent, location: String): CurrentWeatherData

    class Base @Inject constructor() : ForecastToCurrentDataMapper {

        override fun map(forecast: MappableToCurrent, location: String): CurrentWeatherData {
            val mapper = object : CurrentForecastDataMapper {
                override fun map(weatherId: Int, temperature: Double): CurrentWeatherData =
                    CurrentWeatherData(weatherId, temperature, location)
            }
            return forecast.map(mapper)
        }
    }
}