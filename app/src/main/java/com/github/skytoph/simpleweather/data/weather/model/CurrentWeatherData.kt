package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.data.weather.update.UpdateCurrentWeather
import com.github.skytoph.simpleweather.data.weather.cache.CurrentDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.CurrentDBMapper
import com.github.skytoph.simpleweather.data.weather.mapper.CurrentWeatherDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

data class CurrentWeatherData(
    private val weatherId: Int,
    private val temperature: Double,
    private val location: String,
    private val favorite: Boolean,
) : Mappable<WeatherDomain.CurrentWeather, CurrentWeatherDataToDomainMapper>,
    MappableToDB.Embedded<CurrentDB, CurrentDBMapper> {

    override fun map(mapper: CurrentWeatherDataToDomainMapper): WeatherDomain.CurrentWeather =
        mapper.map(weatherId, temperature, location, favorite)

    override fun map(mapper: CurrentDBMapper): CurrentDB = mapper.map(weatherId, temperature, location)

    fun update(mapper: UpdateCurrentWeather) = mapper.update(location, favorite)
}
