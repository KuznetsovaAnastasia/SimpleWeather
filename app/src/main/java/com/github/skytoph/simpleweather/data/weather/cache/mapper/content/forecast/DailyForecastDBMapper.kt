package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.horizon.HorizonDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.DailyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import javax.inject.Inject

interface DailyForecastDBMapper : Mapper<DailyForecastDB> {

    fun map(
        time: Long,
        tempMin: Double,
        tempMax: Double,
        weatherId: Int,
        pop: Double,
        horizon: HorizonData,
        parent: ForecastDB,
        dataBase: DataBase,
    ): DailyForecastDB

    class Base @Inject constructor(private val horizonMapper: HorizonDBMapper) :
        DailyForecastDBMapper {

        override fun map(
            time: Long,
            tempMin: Double,
            tempMax: Double,
            weatherId: Int,
            pop: Double,
            horizon: HorizonData,
            parent: ForecastDB,
            dataBase: DataBase,
        ): DailyForecastDB =
            dataBase.createEmbeddedObject<DailyForecastDB>(parent, ForecastDB.FIELD_DAILY).apply {
                this.time = time
                this.tempMin = tempMin
                this.tempMax = tempMax
                this.weatherId = weatherId
                this.precipitationProb = pop
                this.horizon = horizon.map(horizonMapper)
            }
    }
}