package com.github.skytoph.simpleweather.data.weather.mapper.content.current

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData
import javax.inject.Inject

interface CurrentWeatherDataMapper : Mapper<CurrentWeatherData> {

    fun map(weatherId: Int, temperature: Double, location: Map<String, String>): CurrentWeatherData

    class Base @Inject constructor() : CurrentWeatherDataMapper {

        override fun map(weatherId: Int, temperature: Double, location: Map<String, String>) =
            CurrentWeatherData(weatherId, temperature, location)
    }
}