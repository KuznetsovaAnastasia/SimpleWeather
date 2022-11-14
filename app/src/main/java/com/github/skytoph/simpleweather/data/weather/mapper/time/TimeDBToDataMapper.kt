package com.github.skytoph.simpleweather.data.weather.mapper.time

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.time.ForecastTimeData
import javax.inject.Inject

interface TimeDBToDataMapper : Mapper<ForecastTimeData> {
    fun map(time: Long, timezoneOffset: Int, timezone: String): ForecastTimeData

    class Base @Inject constructor() : TimeDBToDataMapper {

        override fun map(time: Long, timezoneOffset: Int, timezone: String) =
            ForecastTimeData(time, timezoneOffset, timezone)
    }
}
