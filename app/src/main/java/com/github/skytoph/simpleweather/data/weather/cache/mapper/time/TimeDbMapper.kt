package com.github.skytoph.simpleweather.data.weather.cache.mapper.time

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.model.time.TimeDB
import javax.inject.Inject

interface TimeDBMapper : Mapper<TimeDB> {
    fun map(time: Long, timezoneOffset: Int, timezone: String): TimeDB

    class Base @Inject constructor() : TimeDBMapper {

        override fun map(time: Long, timezoneOffset: Int, timezone: String): TimeDB =
            TimeDB().apply {
                this.time = time
                this.timezone = timezone
                this.timezoneOffset = timezoneOffset
            }
    }
}