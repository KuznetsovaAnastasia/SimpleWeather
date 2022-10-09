package com.github.skytoph.simpleweather.data.weather.cache.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.model.HorizonData
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class HorizonDB : RealmObject(), Mappable<HorizonData, HorizonDataMapper> {
    var sunrise: Long = -1
    var sunset: Long = -1
    var currentTime: Long = -1

    override fun map(mapper: HorizonDataMapper): HorizonData =
        mapper.map(sunrise, sunset, currentTime)
}