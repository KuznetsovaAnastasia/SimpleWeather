package com.github.skytoph.simpleweather.data.weather.cache.model.content

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.mapper.content.ContentDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.ContentData
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField

@RealmClass(embedded = true)
open class ContentDB : RealmObject(), Mappable<ContentData, ContentDBToDataMapper> {

    @RealmField(name = FIELD_CURRENT)
    var current: CurrentDB? = null

    @RealmField(name = FIELD_INDICATORS)
    var indicators: IndicatorsDB? = null

    @RealmField(name = FIELD_HORIZON)
    var horizon: HorizonDB? = null

    @RealmField(name = FIELD_FORECAST)
    var forecast: ForecastDB? = null

    companion object {
        const val FIELD_CURRENT = "current"
        const val FIELD_INDICATORS = "indicators"
        const val FIELD_HORIZON = "horizon"
        const val FIELD_FORECAST = "forecast"
    }

    override fun map(mapper: ContentDBToDataMapper): ContentData =
        mapper.map(current!!, indicators!!, horizon!!, forecast!!)
}