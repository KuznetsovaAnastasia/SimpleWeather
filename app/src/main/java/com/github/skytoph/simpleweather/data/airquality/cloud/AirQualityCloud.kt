package com.github.skytoph.simpleweather.data.airquality.cloud

import com.github.skytoph.simpleweather.core.MappableTo
import com.squareup.moshi.Json

data class AirQualityCloud(
    @field:Json(name = "list")
    private val airQualityList: List<AirQualityListItemCloud>?,

    ) : MappableTo<Int> {

    override fun map(): Int = airQualityList?.get(0)?.map() ?: -1
}

data class AirQualityListItemCloud(
    @field:Json(name = "main")
    private val airQualityIndex: AirQualityIndexCloud,
    @field:Json(name = "dt")
    private val time: Long,
) : MappableTo<Int> {

    override fun map(): Int = airQualityIndex.map()
}


data class AirQualityIndexCloud(
    @field:Json(name = "aqi")
    private val aqi: Int,
) : MappableTo<Int> {

    override fun map(): Int = aqi
}
