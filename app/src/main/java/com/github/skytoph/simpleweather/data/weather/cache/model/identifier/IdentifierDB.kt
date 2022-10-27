package com.github.skytoph.simpleweather.data.weather.cache.model.identifier

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.identifier.IdentifierDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.identifier.IdentifierData
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField

@RealmClass(embedded = true)
open class IdentifierDB : RealmObject(), Mappable<IdentifierData, IdentifierDBToDataMapper> {

    @RealmField(name = FIELD_PLACE_ID)
    var placeId: String = ""

    @RealmField(name = FIELD_PRIORITY)
    var priority: Int = 0

    companion object {
        const val FIELD_PLACE_ID = "place_id"
        const val FIELD_PRIORITY = "priority"
    }

    override fun map(mapper: IdentifierDBToDataMapper): IdentifierData = mapper.map(placeId, priority)
}