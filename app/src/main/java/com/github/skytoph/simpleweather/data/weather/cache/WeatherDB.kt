package com.github.skytoph.simpleweather.data.weather.cache

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.*
import com.github.skytoph.simpleweather.data.weather.model.*
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField

open class WeatherDB : RealmObject(), Mappable<WeatherData, WeatherDBToDataMapper> {

    @PrimaryKey
    @RealmField(name = FIELD_ID)
    var id: String = ""

    @RealmField(name = FIELD_PRIORITY)
    var priority: Int = 0
    var current: CurrentDB? = null
    var indicators: IndicatorsDB? = null
    var horizon: HorizonDB? = null
    var warnings: RealmList<WarningDB> = RealmList()
    var hourly: RealmList<HourlyForecastDB> = RealmList()
    var daily: RealmList<DailyForecastDB> = RealmList()

    override fun map(mapper: WeatherDBToDataMapper): WeatherData =
        mapper.map(
            id,
            current!!,
            indicators!!,
            horizon!!,
            warnings.toList(),
            hourly.toList(),
            daily.toList(),
        )

    companion object {
        const val FIELD_ID = "id"
        const val FIELD_PRIORITY = "priority"
    }
}

@RealmClass(embedded = true)
open class CurrentDB(
    var weatherCode: Int = -1,
    var temperature: Double = 0.0,
    var location: String = "",
) : RealmObject(), Mappable<CurrentWeatherData, CurrentWeatherDataMapper> {

    override fun map(mapper: CurrentWeatherDataMapper): CurrentWeatherData =
        mapper.map(weatherCode, temperature, location)
}

@RealmClass(embedded = true)
open class IndicatorsDB : RealmObject(), Mappable<IndicatorsData, IndicatorsDataMapper> {
    var time: Long = -1
    var uvi: Double = 0.0
    var precipitationProb: Double = -1.0
    var airQuality: Int = -1

    override fun map(mapper: IndicatorsDataMapper) =
        mapper.map(time, uvi, precipitationProb, airQuality)
}

@RealmClass(embedded = true)
open class HorizonDB : RealmObject(), Mappable<HorizonData, HorizonDataMapper> {
    var sunrise: Long = -1
    var sunset: Long = -1
    var currentTime: Long = -1

    override fun map(mapper: HorizonDataMapper): HorizonData =
        mapper.map(sunrise, sunset, currentTime)
}

@RealmClass(embedded = true)
open class WarningDB : RealmObject(), Mappable<AlertData, AlertDataMapper> {
    var title: String = ""
    var description: String = ""
    var expectedTime: Long = 0

    override fun map(mapper: AlertDataMapper): AlertData =
        mapper.map(title, expectedTime, description)
}

@RealmClass(embedded = true)
open class HourlyForecastDB : RealmObject(),
    Mappable<HourlyForecastData, HourlyForecastDataMapper> {

    var time: Long = 0
    var temp: Double = 0.0
    var weatherId: Int = 0
    var precipitationProb: Double = 0.0

    override fun map(mapper: HourlyForecastDataMapper): HourlyForecastData =
        mapper.map(time, temp, weatherId, precipitationProb)
}

@RealmClass(embedded = true)
open class DailyForecastDB : RealmObject(),
    Mappable<DailyForecastData, DailyForecastDataMapper> {

    var time: Long = 0
    var tempMin: Double = 0.0
    var tempMax: Double = 0.0
    var weatherId: Int = 0
    var precipitationProb: Double = 0.0

    override fun map(mapper: DailyForecastDataMapper): DailyForecastData =
        mapper.map(time, tempMin, tempMax, weatherId, precipitationProb)
}