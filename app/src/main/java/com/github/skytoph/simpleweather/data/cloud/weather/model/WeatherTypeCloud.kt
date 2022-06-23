package com.github.skytoph.simpleweather.data.cloud.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.cloud.WeatherTypeCloudMapper
import com.squareup.moshi.Json

data class WeatherTypeCloud(
    @field:Json(name="id")
    private val id: Int,

    @field:Json(name="main")
    private val name: String
) : Mappable<Pair<Int, String>, WeatherTypeCloudMapper> {

    override fun map(mapper: WeatherTypeCloudMapper): Pair<Int, String> = mapper.map(id, name)
}