package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.DailyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import javax.inject.Inject

interface DailyForecastDBMapper : Mapper<DailyForecastDB> {

    fun map(
        time: Long,
        tempMin: Double,
        tempMax: Double,
        weatherId: Int,
        pop: Double,
        parent: ForecastDB,
        dataBase: DataBase,
    ): DailyForecastDB

    class Base @Inject constructor() : DailyForecastDBMapper {

        override fun map(
            time: Long,
            tempMin: Double,
            tempMax: Double,
            weatherId: Int,
            pop: Double,
            parent: ForecastDB,
            dataBase: DataBase,
        ): DailyForecastDB =
            dataBase.createEmbeddedObject<DailyForecastDB>(parent, ForecastDB.FIELD_DAILY).apply {
                this.time = time
                this.tempMin = tempMin
                this.tempMax = tempMax
                this.weatherId = weatherId
                this.precipitationProb = pop
            }
    }
}