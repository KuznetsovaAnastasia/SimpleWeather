package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.data.weather.model.*
import javax.inject.Inject

interface WeatherDataDBMapper : Mapper<WeatherDB> {
    fun map(
        id: String,
        placeId: String,
        currentWeatherData: CurrentWeatherData,
        indicatorsData: IndicatorsData,
        horizonData: HorizonData,
        alerts: List<AlertData>,
        hourlyForecast: List<HourlyForecastData>,
        dailyForecast: List<DailyForecastData>,
        dataBase: DataBase,
        priority: Int,
    ): WeatherDB

    class Base @Inject constructor(
        private val currentMapper: CurrentDBMapper,
        private val indicatorsMapper: IndicatorsDBMapper,
        private val horizonMapper: HorizonDBMapper,
        private val warningsDBMapper: WarningsDBMapper,
        private val hourlyDBMapper: HourlyForecastListDBMapper,
        private val dailyDBMapper: DailyForecastListDBMapper,
    ) : WeatherDataDBMapper {

        override fun map(
            id: String,
            placeId: String,
            currentWeatherData: CurrentWeatherData,
            indicatorsData: IndicatorsData,
            horizonData: HorizonData,
            alerts: List<AlertData>,
            hourlyForecast: List<HourlyForecastData>,
            dailyForecast: List<DailyForecastData>,
            dataBase: DataBase,
            priority: Int,
        ): WeatherDB = dataBase.createObject<WeatherDB>(id).apply {
            this.placeId = placeId
            this.current = currentWeatherData.map(currentMapper)
            this.indicators = indicatorsData.map(indicatorsMapper)
            this.horizon = horizonData.map(horizonMapper)
            this.horizon = horizonData.map(horizonMapper)
            this.priority =
                if (priority != 0) priority
                else dataBase.findMax<WeatherDB>(WeatherDB.FIELD_PRIORITY) + 1
            warningsDBMapper.map(alerts, dataBase, this)
            hourlyDBMapper.map(hourlyForecast, dataBase, this)
            dailyDBMapper.map(dailyForecast, dataBase, this)
        }
    }
}
