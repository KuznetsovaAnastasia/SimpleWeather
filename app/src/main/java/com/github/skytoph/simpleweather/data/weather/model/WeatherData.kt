package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.core.data.SaveItem
import com.github.skytoph.simpleweather.core.data.SaveItemToCache
import com.github.skytoph.simpleweather.data.weather.cache.WeatherDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDataDBMapper
import com.github.skytoph.simpleweather.data.weather.mapper.WeatherDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

sealed class WeatherData : Mappable<WeatherDomain, WeatherDataToDomainMapper>,
    MappableToDB.Base<WeatherDB, WeatherDataDBMapper>,
    SaveItemToCache<WeatherData> {

    data class Info(
        private val id: String,
        private val currentWeatherData: CurrentWeatherData,
        private val indicatorsData: IndicatorsData,
        private val horizonData: HorizonData,
        private val alertData: List<AlertData>,
    ) : WeatherData() {

        override fun map(mapper: WeatherDataToDomainMapper): WeatherDomain =
            mapper.map(id, currentWeatherData, indicatorsData, horizonData, alertData)

        override fun map(mapper: WeatherDataDBMapper, dataBase: DataBase): WeatherDB =
            mapper.map(id, currentWeatherData, indicatorsData, horizonData, dataBase)

        override suspend fun save(source: SaveItem<WeatherData>) =
            source.saveOrUpdate(id, this)
    }


    data class Fail(private val exception: Exception) : WeatherData() {

        override fun map(mapper: WeatherDataToDomainMapper): WeatherDomain = mapper.map(exception)

        override fun map(mapper: WeatherDataDBMapper, dataBase: DataBase): WeatherDB =
            throw IllegalStateException("can not save failed weather data")

        override suspend fun save(source: SaveItem<WeatherData>) = Unit
    }
}