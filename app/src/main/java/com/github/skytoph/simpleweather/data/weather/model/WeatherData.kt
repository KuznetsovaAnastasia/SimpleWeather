package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.core.data.Item
import com.github.skytoph.simpleweather.core.data.SaveItem
import com.github.skytoph.simpleweather.core.data.UpdateItem
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDataDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.data.weather.mapper.WeatherDataToDomainMapper
import com.github.skytoph.simpleweather.data.weather.model.content.ContentData
import com.github.skytoph.simpleweather.data.weather.model.identifier.IdentifierData
import com.github.skytoph.simpleweather.data.weather.model.time.ForecastTimeData
import com.github.skytoph.simpleweather.data.weather.update.UpdateWeather
import com.github.skytoph.simpleweather.data.weather.update.UpdateWeatherLocation
import com.github.skytoph.simpleweather.domain.weather.RefreshLocation
import com.github.skytoph.simpleweather.domain.weather.SaveState
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

data class WeatherData(
    private val identifier: IdentifierData,
    private val time: ForecastTimeData,
    private val content: ContentData,
) : Mappable<WeatherDomain, WeatherDataToDomainMapper>,
    MappableToDB.Base<WeatherDB, WeatherDataDBMapper>,
    Item<WeatherData, IdentifierData>,
    SaveState,
    IdMapper.MappableToCoordinates {

    override fun saveState(refreshLocation: RefreshLocation.SaveRefreshed) =
        identifier.saveState(refreshLocation)

    override fun map(mapper: WeatherDataToDomainMapper): WeatherDomain =
        mapper.map(identifier, time, content)

    override fun map(mapper: WeatherDataDBMapper, dataBase: DataBase): WeatherDB =
        mapper.map(identifier, time, content, dataBase)

    override fun map(mapper: IdMapper): Pair<Double, Double> = identifier.map(mapper)

    override suspend fun save(source: SaveItem<WeatherData>) =
        source.saveOrUpdate(identifier.map(), this)

    fun update(mapper: UpdateWeather): WeatherData =mapper.update(identifier, time, content)

    fun update(mapper: UpdateWeatherLocation): WeatherData = mapper.update(identifier, time, content)

    override suspend fun update(source: UpdateItem<WeatherData, IdentifierData>): WeatherData = source.update(this)

    override suspend fun updateLocation(source: UpdateItem<WeatherData, IdentifierData>): WeatherData =
        source.updateLocation(this, identifier)
}