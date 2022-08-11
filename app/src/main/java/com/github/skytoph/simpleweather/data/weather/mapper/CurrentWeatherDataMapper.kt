package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.CurrentWeatherData
import javax.inject.Inject

interface CurrentWeatherDataMapper : Mapper<CurrentWeatherData> {

    fun map(weatherId: Int, temperature: Double, location: String): CurrentWeatherData

    class Base @Inject constructor() : CurrentWeatherDataMapper {
        override fun map(weatherId: Int, temperature: Double, location: String) =
            CurrentWeatherData(weatherId, temperature, location, true)
    }
}
