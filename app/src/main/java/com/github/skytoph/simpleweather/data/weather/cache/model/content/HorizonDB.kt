package com.github.skytoph.simpleweather.data.weather.cache.model.content

import com.github.skytoph.simpleweather.data.weather.mapper.content.horizon.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class HorizonDB : RealmObject(), MappableToHorizon {
    var sunrise: Long = -1
    var sunset: Long = -1

    override fun map(mapper: HorizonDataMapper): HorizonData =
        mapper.map(sunrise, sunset)
}

interface MappableToHorizon {
    fun map(mapper: HorizonDataMapper): HorizonData
}