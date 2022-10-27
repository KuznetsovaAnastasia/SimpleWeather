package com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.AlertDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.AlertData
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class WarningDB : RealmObject(), Mappable<AlertData, AlertDataMapper> {
    var title: String = ""
    var description: String = ""
    var expectedTime: Long = 0

    override fun map(mapper: AlertDataMapper): AlertData =
        mapper.map(title, expectedTime, description)
}