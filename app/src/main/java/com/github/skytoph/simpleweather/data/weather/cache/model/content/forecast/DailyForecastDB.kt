package com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.cache.model.content.HorizonDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.MappableToHorizon
import com.github.skytoph.simpleweather.data.weather.mapper.content.current.CurrentForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.DailyForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.horizon.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.indicators.IndicatorsDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField

@RealmClass(embedded = true)
open class DailyForecastDB : RealmObject(),
    Mappable<DailyForecastData, DailyForecastDataMapper>,
    MappableToHorizon,
    MappableToForecast {

    var time: Long = 0
    var tempMin: Double = 0.0
    var tempMax: Double = 0.0
    var weatherId: Int = 0
    var precipitationProb: Double = 0.0
    var uvi: Double = 0.0

    @RealmField(name = FIELD_HORIZON)
    var horizon: HorizonDB? = null

    override fun map(mapper: DailyForecastDataMapper): DailyForecastData =
        mapper.map(time, tempMin, tempMax, weatherId, precipitationProb, uvi, horizon!!)

    override fun isCurrent(seconds: Long) = time == seconds

    override fun map(mapper: CurrentForecastDataMapper): CurrentWeatherData =
        mapper.map(weatherId, arrayOf(tempMin, tempMax).average())

    override fun map(mapper: IndicatorsDataMapper): IndicatorsData =
        mapper.map(uvi, precipitationProb)

    override fun map(mapper: HorizonDataMapper): HorizonData = horizon!!.map(mapper)

    companion object {
        const val FIELD_HORIZON = "horizon"
    }
}