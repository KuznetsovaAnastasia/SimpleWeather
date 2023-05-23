package com.github.skytoph.simpleweather.data.weather.model.content.current

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.current.LocationDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.ContentDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.LocationDB
import com.github.skytoph.simpleweather.data.weather.mapper.CurrentWeatherDomainMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.data.weather.update.UpdateCurrentWeather
import com.github.skytoph.simpleweather.data.weather.update.UpdateCurrentWeatherLocation
import com.github.skytoph.simpleweather.domain.weather.model.CurrentWeatherDomain

data class CurrentWeatherData(
    private val weatherId: Int,
    private val temperature: Double,
    private val location: Map<String, String>,
) : Mappable<CurrentWeatherDomain, CurrentWeatherDomainMapper>,
    MappableToDB.EmbeddedList<LocationDB, ContentDB, LocationDBMapper> {

    override fun map(mapper: CurrentWeatherDomainMapper): CurrentWeatherDomain =
        mapper.map(weatherId, temperature, location)

    override fun map(mapper: LocationDBMapper, dataBase: DataBase, parent: ContentDB) =
        mapper.map(location, parent, dataBase)

    fun update(mapper: UpdateCurrentWeatherLocation): WeatherData =
        mapper.update(weatherId, temperature)

    fun update(mapper: UpdateCurrentWeather): CurrentWeatherData = mapper.update(location)
}
