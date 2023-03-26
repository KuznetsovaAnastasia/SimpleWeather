package com.github.skytoph.simpleweather.data.airquality.cloud

import com.github.skytoph.simpleweather.core.MappableTo
import com.squareup.moshi.Json

data class AirQualityCloud(
    @Json(name = "list")
    private val list: List<AirQualityListItemCloud>?,
) : MappableTo<Int> {

    override fun map(): Int = if (list.isNullOrEmpty()) -1 else list[0].map()
}

data class AirQualityListItemCloud(
    @Json(name = "main")
    private val main: AirQualityIndexCloud,
    @Json(name = "dt")
    private val dt: Long,
) : MappableTo<Int> {

    override fun map(): Int = main.map()
}


data class AirQualityIndexCloud(
    @Json(name = "aqi")
    private val aqi: Int,
) : MappableTo<Int> {

    override fun map(): Int = aqi
}
