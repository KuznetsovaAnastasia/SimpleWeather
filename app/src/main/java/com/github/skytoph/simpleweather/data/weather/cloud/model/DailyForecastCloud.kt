package com.github.skytoph.simpleweather.data.weather.cloud.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.DailyForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import com.squareup.moshi.Json

data class DailyForecastCloud(
    @Json(name = "dt")
    private val dt: Long,

    @Json(name = "temp")
    private val temp: DailyTempCloud?,

    @Json(name = "weather")
    private val weather: List<WeatherTypeCloud>?,

    @Json(name = "pop")
    private val pop: Double,

    @Json(name = "uvi")
    private val uvi: Double,

    @Json(name = "sunrise")
    private val sunrise: Long,

    @Json(name = "sunset")
    private val sunset: Long,
) : Mappable<DailyForecastData, DailyForecastDataMapper>, MappableTo<Double> {

    override fun map(mapper: DailyForecastDataMapper): DailyForecastData =
        mapper.map(
            time = dt,
            temp = temp?.map() ?: Pair(0.0, 0.0),
            weatherId = weather?.firstOrNull()?.map() ?: 0,
            pop = pop,
            uvi = uvi,
            sunrise = sunrise,
            sunset = sunset
        )

    override fun map(): Double = pop
}

data class DailyTempCloud(
    @Json(name = "min")
    private val min: Double,
    @Json(name = "max")
    private val max: Double,
) : MappableTo<Pair<Double, Double>> {

    override fun map(): Pair<Double, Double> = Pair(min, max)
}