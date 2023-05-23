package com.github.skytoph.simpleweather.data.weather.cache.model.content

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.mapper.content.ContentDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.ContentData
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField

@RealmClass(embedded = true)
open class ContentDB : RealmObject(), Mappable<ContentData, ContentDBToDataMapper> {

    @RealmField(name = FIELD_LOCATION)
    var location: RealmList<LocationDB> = RealmList()

    @RealmField(name = FIELD_AIR_QUALITY)
    var airQuality: Int = -1

    @RealmField(name = FIELD_FORECAST)
    var forecast: ForecastDB? = null

    companion object {
        const val FIELD_LOCATION = "location"
        const val FIELD_AIR_QUALITY = "air_quality"
        const val FIELD_FORECAST = "forecast"
    }

    override fun map(mapper: ContentDBToDataMapper): ContentData =
        mapper.map(location, airQuality, forecast!!)
}