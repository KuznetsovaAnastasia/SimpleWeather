package com.github.skytoph.simpleweather.data.weather.model.time

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.time.TimeDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.time.TimeDB
import com.github.skytoph.simpleweather.data.weather.mapper.ProvidesOffset
import com.github.skytoph.simpleweather.data.weather.mapper.TimeDomainMapper
import com.github.skytoph.simpleweather.data.weather.mapper.Timezone
import com.github.skytoph.simpleweather.data.weather.mapper.TimezoneMapper

data class ForecastTimeData(
    private val time: Long,
    private val timezoneOffset: Int,
    private val timezone: String,
) : MappableToDB.Embedded<TimeDB, TimeDBMapper>,
    Mappable<Long, TimeDomainMapper>,
    ProvidesOffset {

    override fun map(mapper: TimeDBMapper): TimeDB = mapper.map(time, timezoneOffset, timezone)

    override fun map(mapper: TimeDomainMapper): Long = mapper.map(time)

    override fun map(mapper: TimezoneMapper): Timezone = mapper.map(timezoneOffset, timezone)
}