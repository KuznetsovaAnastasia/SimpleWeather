package com.github.skytoph.simpleweather.data.weather.cloud.model

import com.github.skytoph.simpleweather.core.MappableTo
import com.squareup.moshi.Json

data class WeatherTypeCloud(
    @field:Json(name = "id")
    private val id: Int,
) : MappableTo<Int> {

    override fun map(): Int = id
}