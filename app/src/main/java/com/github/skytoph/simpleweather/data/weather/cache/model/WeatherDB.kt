package com.github.skytoph.simpleweather.data.weather.cache.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmField

open class WeatherDB : RealmObject(), Mappable<WeatherData, WeatherDBToDataMapper> {

    @PrimaryKey
    @RealmField(name = FIELD_ID)
    var id: String = ""

    var placeId: String = ""

    @RealmField(name = FIELD_PRIORITY)
    var priority: Int = 0

    @RealmField(name = FIELD_CURRENT)
    var current: CurrentDB? = null

    @RealmField(name = FIELD_INDICATORS)
    var indicators: IndicatorsDB? = null

    @RealmField(name = FIELD_HORIZON)
    var horizon: HorizonDB? = null

    @RealmField(name = FIELD_WARNINGS)
    var warnings: RealmList<WarningDB> = RealmList()

    @RealmField(name = FIELD_HOURLY)
    var hourly: RealmList<HourlyForecastDB> = RealmList()

    @RealmField(name = FIELD_DAILY)
    var daily: RealmList<DailyForecastDB> = RealmList()

    companion object {
        const val FIELD_ID = "id"
        const val FIELD_PRIORITY = "priority"
        const val FIELD_CURRENT = "current"
        const val FIELD_INDICATORS = "indicators"
        const val FIELD_HORIZON = "horizon"
        const val FIELD_WARNINGS = "warnings"
        const val FIELD_HOURLY = "hourly"
        const val FIELD_DAILY = "daily"
    }

    override fun map(mapper: WeatherDBToDataMapper): WeatherData =
        mapper.map(
            id,
            placeId,
            priority,
            current!!,
            indicators!!,
            horizon!!,
            warnings.toList(),
            hourly.toList(),
            daily.toList(),
        )
}