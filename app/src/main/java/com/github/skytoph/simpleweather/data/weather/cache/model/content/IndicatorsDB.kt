package com.github.skytoph.simpleweather.data.weather.cache.model.content

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.content.indicators.IndicatorsDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class IndicatorsDB : RealmObject(), Mappable<IndicatorsData, IndicatorsDataMapper> {
    var uvi: Double = 0.0
    var precipitationProb: Double = -1.0
    var airQuality: Int = -1

    override fun map(mapper: IndicatorsDataMapper) =
        mapper.map(uvi, precipitationProb, airQuality)
}