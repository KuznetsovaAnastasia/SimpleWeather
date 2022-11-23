package com.github.skytoph.simpleweather.data.weather.cache.mapper.content

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.current.CurrentDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast.ForecastDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.indicators.IndicatorsDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.ContentDB
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.ForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData
import javax.inject.Inject

interface ContentDBMapper : Mapper<ContentDB> {
    fun map(
        currentWeather: CurrentWeatherData,
        indicators: IndicatorsData,
        horizon: HorizonData,
        forecast: ForecastData,
        parent: WeatherDB,
        database: DataBase,
    ): ContentDB

    class Base @Inject constructor(private val forecastMapper: ForecastDBMapper) :
        ContentDBMapper {

        override fun map(
            currentWeather: CurrentWeatherData,
            indicators: IndicatorsData,
            horizon: HorizonData,
            forecast: ForecastData,
            parent: WeatherDB,
            database: DataBase,
        ): ContentDB =
            database.createEmbeddedObject<ContentDB>(parent, WeatherDB.FIELD_CONTENT).apply {
                currentWeather.map(object : CurrentDBMapper {
                    override fun map(location: String) {
                        this@apply.location = location
                    }
                })
                indicators.map(object : IndicatorsDBMapper {
                    override fun map(airQuality: Int) {
                        this@apply.airQuality = airQuality
                    }
                })
                forecast.map(forecastMapper, database, this)
            }
    }
}