package com.github.skytoph.simpleweather.data.weather.cache.model.time

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.time.TimeDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.time.ForecastTimeData
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField

@RealmClass(embedded = true)
open class TimeDB : RealmObject(), Mappable<ForecastTimeData, TimeDBToDataMapper> {

    @RealmField(name = FIELD_FORECAST_TIME)
    var time: Long = 0

    @RealmField(name = FIELD_TIMEZONE)
    var timezone: String = ""

    @RealmField(name = FIELD_TIMEZONE_OFFSET)
    var timezoneOffset: Int = 0

    companion object {
        const val FIELD_FORECAST_TIME = "forecast_time"
        const val FIELD_TIMEZONE = "timezone"
        const val FIELD_TIMEZONE_OFFSET = "offset"
    }

    override fun map(mapper: TimeDBToDataMapper): ForecastTimeData =
        mapper.map(time, timezoneOffset, timezone)
}