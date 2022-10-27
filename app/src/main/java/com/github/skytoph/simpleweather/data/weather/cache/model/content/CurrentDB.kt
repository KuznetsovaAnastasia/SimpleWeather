package com.github.skytoph.simpleweather.data.weather.cache.model.content

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.content.current.CurrentWeatherDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class CurrentDB(
    var weatherCode: Int = -1,
    var temperature: Double = 0.0,
    var location: String = "",
) : RealmObject(), Mappable<CurrentWeatherData, CurrentWeatherDataMapper> {

    override fun map(mapper: CurrentWeatherDataMapper): CurrentWeatherData =
        mapper.map(weatherCode, temperature, location)
}