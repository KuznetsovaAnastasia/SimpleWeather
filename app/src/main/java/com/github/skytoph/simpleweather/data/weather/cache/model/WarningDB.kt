package com.github.skytoph.simpleweather.data.weather.cache.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.AlertDataMapper
import com.github.skytoph.simpleweather.data.weather.model.AlertData
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