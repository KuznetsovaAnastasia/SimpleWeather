package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.content.ContentDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.AlertData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import javax.inject.Inject

interface ForecastDBMapper : Mapper<ForecastDB> {
    fun map(
        alerts: List<AlertData>,
        hourlyForecast: List<HourlyForecastData>,
        dailyForecast: List<DailyForecastData>,
        parent: ContentDB,
        database: DataBase
    ): ForecastDB

    class Base @Inject constructor(
        private val warningsDBMapper: WarningsDBMapper,
        private val hourlyDBMapper: HourlyForecastListDBMapper,
        private val dailyDBMapper: DailyForecastListDBMapper,
    ) : ForecastDBMapper {

        override fun map(
            alerts: List<AlertData>,
            hourlyForecast: List<HourlyForecastData>,
            dailyForecast: List<DailyForecastData>,
            parent: ContentDB,
            database: DataBase
        ): ForecastDB = database.createEmbeddedObject<ForecastDB>(parent, ContentDB.FIELD_FORECAST).apply {
            warningsDBMapper.map(alerts, database, this)
            hourlyDBMapper.map(hourlyForecast, database, this)
            dailyDBMapper.map(dailyForecast, database, this)
        }
    }
}