package com.github.skytoph.simpleweather.data.weather.mapper.content.current

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData

interface CurrentForecastDataMapper : Mapper<CurrentWeatherData> {

    fun map(weatherId: Int, temperature: Double): CurrentWeatherData
}