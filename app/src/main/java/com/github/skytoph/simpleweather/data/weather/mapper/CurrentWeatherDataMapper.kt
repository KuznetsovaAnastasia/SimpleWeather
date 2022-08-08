package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.CurrentWeatherData

interface CurrentWeatherDataMapper : Mapper<CurrentWeatherData> {

    fun map(weatherId: Int, temperature: Double): CurrentWeatherData

    class Base : CurrentWeatherDataMapper {
        override fun map(weatherId: Int, temperature: Double) =
            CurrentWeatherData(weatherId, temperature)
    }
}
