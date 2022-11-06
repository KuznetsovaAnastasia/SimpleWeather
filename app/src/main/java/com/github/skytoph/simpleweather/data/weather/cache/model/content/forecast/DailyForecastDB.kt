package com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.content.current.CurrentForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.DailyForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class DailyForecastDB : RealmObject(),
    Mappable<DailyForecastData, DailyForecastDataMapper>,
    MappableToForecast {

    var time: Long = 0
    var tempMin: Double = 0.0
    var tempMax: Double = 0.0
    var weatherId: Int = 0
    var precipitationProb: Double = 0.0

    override fun map(mapper: DailyForecastDataMapper): DailyForecastData =
        mapper.map(time, tempMin, tempMax, weatherId, precipitationProb)

    override fun isCurrent(seconds: Long) = time == seconds

    override fun map(mapper: CurrentForecastDataMapper): CurrentWeatherData =
        mapper.map(weatherId, arrayOf(tempMin, tempMax).average())
}