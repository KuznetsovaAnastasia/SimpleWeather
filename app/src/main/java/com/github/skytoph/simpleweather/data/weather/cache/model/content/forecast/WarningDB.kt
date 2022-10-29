package com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.WarningDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.WarningData
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class WarningDB : RealmObject(), Mappable<WarningData, WarningDataMapper> {
    var title: String = ""
    var description: String = ""
    var expectedTime: Long = 0

    override fun map(mapper: WarningDataMapper): WarningData =
        mapper.map(title, expectedTime, description)
}