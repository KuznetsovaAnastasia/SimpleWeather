package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.WeatherDB
import com.github.skytoph.simpleweather.data.weather.cache.DailyForecastDB
import javax.inject.Inject

interface DailyForecastDBMapper : Mapper<DailyForecastDB> {

    fun map(
        time: Long,
        tempMin: Double,
        tempMax: Double,
        weatherId: Int,
        pop: Double,
        parent: WeatherDB,
        dataBase: DataBase,
    ): DailyForecastDB

    class Base @Inject constructor() : DailyForecastDBMapper {

        override fun map(
            time: Long,
            tempMin: Double,
            tempMax: Double,
            weatherId: Int,
            pop: Double,
            parent: WeatherDB,
            dataBase: DataBase,
        ): DailyForecastDB =
            dataBase.createEmbeddedObject<DailyForecastDB>(parent, "daily").apply {
                this.time = time
                this.tempMin = tempMin
                this.tempMax = tempMax
                this.weatherId = weatherId
                this.precipitationProb = pop
            }
    }
}