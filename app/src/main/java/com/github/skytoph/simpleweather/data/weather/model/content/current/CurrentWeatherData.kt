package com.github.skytoph.simpleweather.data.weather.model.content.current

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.current.CurrentDBMapper
import com.github.skytoph.simpleweather.data.weather.mapper.CurrentWeatherDataToDomainMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.data.weather.update.UpdateCurrentWeather
import com.github.skytoph.simpleweather.data.weather.update.UpdateCurrentWeatherLocation
import com.github.skytoph.simpleweather.domain.weather.model.CurrentWeatherDomain

data class CurrentWeatherData(
    private val weatherId: Int,
    private val temperature: Double,
    private val location: String,
) : Mappable<CurrentWeatherDomain, CurrentWeatherDataToDomainMapper> {

    override fun map(mapper: CurrentWeatherDataToDomainMapper): CurrentWeatherDomain =
        mapper.map(weatherId, temperature, location)

    fun map(mapper: CurrentDBMapper) = mapper.map(location)

    fun update(mapper: UpdateCurrentWeatherLocation): WeatherData =
        mapper.update(weatherId, temperature)

    fun update(mapper: UpdateCurrentWeather): CurrentWeatherData = mapper.update(location)
}
