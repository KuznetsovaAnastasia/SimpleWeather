package com.github.skytoph.simpleweather.data.weather.cloud.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.DailyForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import com.squareup.moshi.Json

data class DailyForecastCloud(
    @field:Json(name = "dt")
    private val time: Long,
    @field:Json(name = "temp")
    private val temp: DailyTempCloud,
    @field:Json(name = "weather")
    private val weather: List<WeatherTypeCloud>,
    @field:Json(name = "pop")
    private val precipitationProb: Double,
    @field:Json(name = "uvi")
    private val uvi: Double,
) : Mappable<DailyForecastData, DailyForecastDataMapper>, MappableTo<Double> {

    override fun map(mapper: DailyForecastDataMapper): DailyForecastData =
        mapper.map(time, temp.map(), weather[0].map(), precipitationProb, uvi)

    override fun map(): Double = precipitationProb
}

data class DailyTempCloud(
    @field:Json(name = "min")
    private val min: Double,
    @field:Json(name = "max")
    private val max: Double,
) : MappableTo<Pair<Double, Double>> {

    override fun map(): Pair<Double, Double> = Pair(min, max)
}