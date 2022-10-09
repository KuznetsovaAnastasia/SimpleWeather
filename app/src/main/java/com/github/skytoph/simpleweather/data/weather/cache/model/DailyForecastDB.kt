package com.github.skytoph.simpleweather.data.weather.cache.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.DailyForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.model.DailyForecastData
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class DailyForecastDB : RealmObject(),
    Mappable<DailyForecastData, DailyForecastDataMapper> {

    var time: Long = 0
    var tempMin: Double = 0.0
    var tempMax: Double = 0.0
    var weatherId: Int = 0
    var precipitationProb: Double = 0.0

    override fun map(mapper: DailyForecastDataMapper): DailyForecastData =
        mapper.map(time, tempMin, tempMax, weatherId, precipitationProb)
}