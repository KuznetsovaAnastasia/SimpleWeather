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

data class WeatherData(
    private val id: String,
    private val currentWeatherData: CurrentWeatherData,
    private val indicatorsData: IndicatorsData,
    private val horizonData: HorizonData,
    private val alertData: List<AlertData>,
    private val hourlyForecast: List<HourlyForecastData>,
    private val dailyForecast: List<DailyForecastData>,
) : Mappable<WeatherDomain, WeatherDataToDomainMapper>,
    MappableToDB.Base<WeatherDB, WeatherDataDBMapper>,
    Item<WeatherData> {

    override fun map(mapper: WeatherDataToDomainMapper): WeatherDomain =
        mapper.map(id,
            currentWeatherData,
            indicatorsData,
            horizonData,
            alertData,
            hourlyForecast,
            dailyForecast)

    override fun map(mapper: WeatherDataDBMapper, dataBase: DataBase): WeatherDB =
        mapper.map(id,
            currentWeatherData,
            indicatorsData,
            horizonData,
            alertData,
            hourlyForecast,
            dailyForecast,
            dataBase)

    fun map(mapper: IdMapper): Pair<Double, Double> = mapper.map(id)

    override suspend fun save(source: SaveItem<WeatherData>) =
        source.saveOrUpdate(id, this)

    fun update(mapper: UpdateWeather): WeatherData = mapper.update(id, currentWeatherData)

    override suspend fun update(source: UpdateItem<WeatherData>): WeatherData = source.update(this)
}