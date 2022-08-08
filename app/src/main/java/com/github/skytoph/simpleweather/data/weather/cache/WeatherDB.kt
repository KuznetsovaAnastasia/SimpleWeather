package com.github.skytoph.simpleweather.data.weather.cache

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.location.mapper.LocationDataMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.CurrentWeatherDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.IndicatorsDataMapper
import com.github.skytoph.simpleweather.data.weather.model.*
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

open class WeatherDB : RealmObject(), Mappable<WeatherData, WeatherDBToDataMapper> {

    @PrimaryKey
    var id: String = ""
    var current: CurrentDB? = null
    var location: LocationDB? = null
    var indicators: IndicatorsDB? = null
    var horizon: HorizonDB? = null

    override fun map(mapper: WeatherDBToDataMapper): WeatherData =
        mapper.map(
            location!!,
            current!!,
            indicators!!,
            horizon!!
        )
}

@RealmClass(embedded = true)
open class LocationDB : RealmObject(), Mappable<LocationData, LocationDataMapper> {
    var id: String = ""
    var lat: Double = 0.0
    var lon: Double = 0.0
    var location: String = ""

    override fun map(mapper: LocationDataMapper): LocationData =
        mapper.map(id, lat, lon, location, true)
}

@RealmClass(embedded = true)
open class CurrentDB : RealmObject(), Mappable<CurrentWeatherData, CurrentWeatherDataMapper> {
    var weatherCode: Int = -1
    var temperature: Double = 0.0

    override fun map(mapper: CurrentWeatherDataMapper): CurrentWeatherData =
        mapper.map(weatherCode, temperature)
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