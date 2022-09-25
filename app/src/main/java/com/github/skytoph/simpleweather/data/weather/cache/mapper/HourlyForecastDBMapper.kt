package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.HourlyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.WeatherDB
import javax.inject.Inject

interface HourlyForecastDBMapper : Mapper<HourlyForecastDB> {

    fun map(
        time: Long, temp: Double, weatherId: Int, pop: Double,
        parent: WeatherDB,
        dataBase: DataBase,
    ): HourlyForecastDB

    class Base @Inject constructor() : HourlyForecastDBMapper {

        override fun map(
            time: Long,
            temp: Double,
            weatherId: Int,
            pop: Double,
            parent: WeatherDB,
            dataBase: DataBase,
        ) = dataBase.createEmbeddedObject<HourlyForecastDB>(parent, "hourly").apply {
            this.time = time
            this.temp = temp
            this.weatherId = weatherId
            this.precipitationProb = pop
        }
    }
}
