package com.github.skytoph.simpleweather.data.weather.mapper.content.current

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.location.mapper.LocalNameDataMapper
import com.github.skytoph.simpleweather.data.location.mapper.LocationDataMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.MappableToCurrent
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData
import javax.inject.Inject

interface ForecastToCurrentDataMapper : Mapper<CurrentWeatherData> {

    fun map(
        forecast: MappableToCurrent,
        location: List<Mappable<Pair<String, String>, LocalNameDataMapper>>
    ): CurrentWeatherData

    class Base @Inject constructor(private val mapper: LocationDataMapper) :
        ForecastToCurrentDataMapper {

        override fun map(
            forecast: MappableToCurrent,
            location: List<Mappable<Pair<String, String>, LocalNameDataMapper>>
        ): CurrentWeatherData = forecast.map(object : CurrentForecastDataMapper {
            override fun map(weatherId: Int, temperature: Double): CurrentWeatherData =
                CurrentWeatherData(weatherId, temperature, mapper.map(location))
        })
    }
}