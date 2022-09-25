package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.HourlyForecastDB
import javax.inject.Inject

interface HourlyForecastDBMapper : Mapper<HourlyForecastDB> {

    fun map(time: Long, temp: Double, weatherId: Int, pop: Double): HourlyForecastDB

    class Base @Inject constructor() : HourlyForecastDBMapper {

        override fun map(time: Long, temp: Double, weatherId: Int, pop: Double) =
            HourlyForecastDB().apply {
                this.time = time
                this.temp = temp
                this.weatherId = weatherId
                this.precipitationProb = pop
            }
    }
}
