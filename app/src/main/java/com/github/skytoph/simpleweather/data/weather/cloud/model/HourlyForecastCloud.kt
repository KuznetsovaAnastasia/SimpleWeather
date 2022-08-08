package com.github.skytoph.simpleweather.data.weather.cloud.model

import com.github.skytoph.simpleweather.core.MappableTo
import com.squareup.moshi.Json

data class HourlyForecastCloud(
    @field:Json(name = "pop")
    private val precipitationProb: Double,
) : MappableTo<Double> {

    override fun map(): Double = precipitationProb

}