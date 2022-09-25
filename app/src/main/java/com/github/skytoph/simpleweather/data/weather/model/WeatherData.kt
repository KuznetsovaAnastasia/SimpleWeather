package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.*
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.weather.update.UpdateWeather
import com.github.skytoph.simpleweather.data.weather.cache.WeatherDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDataDBMapper
import com.github.skytoph.simpleweather.data.weather.mapper.WeatherDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

sealed class WeatherData : Mappable<WeatherDomain, WeatherDataToDomainMapper>,
    MappableToDB.Base<WeatherDB, WeatherDataDBMapper>,
    Item<WeatherData> {

    abstract fun map(mapper: IdMapper): Pair<Double, Double>
    abstract fun update(mapper: UpdateWeather): WeatherData

    data class Info(
        private val id: String,
        private val currentWeatherData: CurrentWeatherData,
        private val indicatorsData: IndicatorsData,
        private val horizonData: HorizonData,
        private val alertData: List<AlertData>,
        private val hourlyForecast: List<HourlyForecastData>,
    ) : WeatherData() {

        override fun map(mapper: WeatherDataToDomainMapper): WeatherDomain =
            mapper.map(id, currentWeatherData, indicatorsData, horizonData, alertData)

        override fun map(mapper: WeatherDataDBMapper, dataBase: DataBase): WeatherDB =
            mapper.map(id, currentWeatherData, indicatorsData, horizonData, alertData, dataBase)

        override fun map(mapper: IdMapper): Pair<Double, Double> = mapper.map(id)

        override suspend fun save(source: SaveItem<WeatherData>) =
            source.saveOrUpdate(id, this)

        override fun update(mapper: UpdateWeather): WeatherData = mapper.update(id, currentWeatherData)

        override suspend fun update(source: UpdateItem<WeatherData>): WeatherData = source.update(this)
    }

    data class Fail(private val exception: Exception) : WeatherData() {

        override fun map(mapper: WeatherDataToDomainMapper): WeatherDomain = mapper.map(exception)

        override fun map(mapper: WeatherDataDBMapper, dataBase: DataBase): WeatherDB =
            throw IllegalStateException("can not save failed weather data")

        override fun map(mapper: IdMapper): Pair<Double, Double> = mapper.map("")

        override suspend fun save(source: SaveItem<WeatherData>) = Unit

        override fun update(mapper: UpdateWeather): WeatherData =
            throw IllegalStateException("can not update failed weather data")

        override suspend fun update(source: UpdateItem<WeatherData>): WeatherData = this
    }
}