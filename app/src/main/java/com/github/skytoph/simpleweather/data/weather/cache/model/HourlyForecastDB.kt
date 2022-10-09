package com.github.skytoph.simpleweather.data.weather.cache.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.HourlyForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.model.HourlyForecastData
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class HourlyForecastDB : RealmObject(),
    Mappable<HourlyForecastData, HourlyForecastDataMapper> {

    var time: Long = 0
    var temp: Double = 0.0
    var weatherId: Int = 0
    var precipitationProb: Double = 0.0

    override fun map(mapper: HourlyForecastDataMapper): HourlyForecastData =
        mapper.map(time, temp, weatherId, precipitationProb)
}