package com.github.skytoph.simpleweather.data.weather.cache.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.ContentDB
import com.github.skytoph.simpleweather.data.weather.cache.model.identifier.IdentifierDB
import com.github.skytoph.simpleweather.data.weather.cache.model.time.TimeDB
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmField

open class WeatherDB : RealmObject(), Mappable<WeatherData, WeatherDBToDataMapper> {

    @PrimaryKey
    @RealmField(name = FIELD_ID)
    var id: String = ""

    @RealmField(name = FIELD_IDENTIFIER)
    var identifier: IdentifierDB? = null

    @RealmField(name = FIELD_TIME)
    var time: TimeDB? = null

    @RealmField(name = FIELD_CONTENT)
    var content: ContentDB? = null

    companion object {
        const val FIELD_ID = "id"
        const val FIELD_IDENTIFIER = "identifier"
        const val FIELD_TIME = "time"
        const val FIELD_CONTENT = "content"
    }

    override fun map(mapper: WeatherDBToDataMapper): WeatherData =
        mapper.map(id, identifier!!, time!!, content!!)
}