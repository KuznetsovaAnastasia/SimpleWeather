package com.github.skytoph.simpleweather.data.weather.cache.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.IndicatorsDataMapper
import com.github.skytoph.simpleweather.data.weather.model.IndicatorsData
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class IndicatorsDB : RealmObject(), Mappable<IndicatorsData, IndicatorsDataMapper> {
    var time: Long = -1
    var uvi: Double = 0.0
    var precipitationProb: Double = -1.0
    var airQuality: Int = -1

    override fun map(mapper: IndicatorsDataMapper) =
        mapper.map(time, uvi, precipitationProb, airQuality)
}