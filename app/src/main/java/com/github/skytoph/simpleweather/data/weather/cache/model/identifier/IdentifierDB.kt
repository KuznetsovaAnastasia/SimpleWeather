package com.github.skytoph.simpleweather.data.weather.cache.model.identifier

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.identifier.IdentifierDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.identifier.IdentifierData
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField

@RealmClass(embedded = true)
open class IdentifierDB : RealmObject(), Mappable<IdentifierData, IdentifierDBToDataMapper> {

    @RealmField(name = FIELD_LAT)
    var lat: Double = 0.0

    @RealmField(name = FIELD_LON)
    var lon: Double = 0.0

    @RealmField(name = FIELD_PRIORITY)
    var priority: Int = 0

    companion object {
        const val FIELD_LAT = "lat"
        const val FIELD_LON = "lon"
        const val FIELD_PRIORITY = "priority"
    }

    override fun map(mapper: IdentifierDBToDataMapper): IdentifierData =
        mapper.map(lat, lon, priority)
}