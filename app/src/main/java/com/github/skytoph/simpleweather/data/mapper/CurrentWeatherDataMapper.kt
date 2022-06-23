package com.github.skytoph.simpleweather.data.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.model.CurrentWeatherData

interface CurrentWeatherDataMapper: Mapper<CurrentWeatherData> {

    fun map(lat: Double, lon: Double, timezone: String): CurrentWeatherData

}
