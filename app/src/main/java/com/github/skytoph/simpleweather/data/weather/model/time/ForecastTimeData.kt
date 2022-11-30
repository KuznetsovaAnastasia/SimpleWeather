package com.github.skytoph.simpleweather.data.weather.model.time

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.time.TimeDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.time.TimeDB
import com.github.skytoph.simpleweather.data.weather.mapper.*
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.data.weather.update.UpdateForecastTime
import com.github.skytoph.simpleweather.domain.weather.mapper.DataUpdatedLatelyCriteria
import com.github.skytoph.simpleweather.domain.weather.mapper.CompareTimeWithCurrent
import com.github.skytoph.simpleweather.domain.weather.mapper.CurrentTimeComparable
import com.github.skytoph.simpleweather.domain.weather.model.CurrentWeatherDomain

data class ForecastTimeData(
    private val time: Long,
    private val timezoneOffset: Int,
    private val timezone: String,
) : MappableToDB.Embedded<TimeDB, TimeDBMapper>,
    MappableToCurrentWeatherDomain<TimeDomainMapper>,
    Mappable<Timezone, TimezoneMapper>,
    ProvidesOffset,
    CurrentTimeComparable {

    override fun map(mapper: TimeDBMapper): TimeDB = mapper.map(time, timezoneOffset, timezone)

    override fun map(mapper: TimeDomainMapper): CurrentWeatherDomain = mapper.map(time)

    override fun map(mapper: TimezoneMapper): Timezone = mapper.map(timezoneOffset, timezone)

    fun update(mapper: UpdateForecastTime): WeatherData = mapper.update(timezoneOffset, timezone)

    override fun updatedLately(mapper: CompareTimeWithCurrent, criteria: DataUpdatedLatelyCriteria): Boolean =
        mapper.compare(time, criteria)
}