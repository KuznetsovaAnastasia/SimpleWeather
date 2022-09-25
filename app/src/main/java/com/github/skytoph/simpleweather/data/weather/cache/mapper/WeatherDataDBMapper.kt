package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.WeatherDB
import com.github.skytoph.simpleweather.data.weather.model.*
import javax.inject.Inject

interface WeatherDataDBMapper : Mapper<WeatherDB> {
    fun map(
        id: String,
        currentWeatherData: CurrentWeatherData,
        indicatorsData: IndicatorsData,
        horizonData: HorizonData,
        alerts: List<AlertData>,
        hourlyForecast: List<HourlyForecastData>,
        dataBase: DataBase,
    ): WeatherDB

    class Base @Inject constructor(
        private val currentMapper: CurrentDBMapper,
        private val indicatorsMapper: IndicatorsDBMapper,
        private val horizonMapper: HorizonDBMapper,
        private val warningsDBMapper: WarningsDBMapper,
        private val hourlyDBMapper: HourlyForecastListDBMapper,
    ) : WeatherDataDBMapper {

        override fun map(
            id: String,
            currentWeatherData: CurrentWeatherData,
            indicatorsData: IndicatorsData,
            horizonData: HorizonData,
            alerts: List<AlertData>,
            hourlyForecast: List<HourlyForecastData>,
            dataBase: DataBase,
        ): WeatherDB = dataBase.createObject<WeatherDB>(id).apply {
            this.current = currentWeatherData.map(currentMapper)
            this.indicators = indicatorsData.map(indicatorsMapper)
            this.horizon = horizonData.map(horizonMapper)
            this.horizon = horizonData.map(horizonMapper)
            warningsDBMapper.map(alerts, dataBase, this)
            hourlyDBMapper.map(hourlyForecast, dataBase, this)
        }
    }
}
