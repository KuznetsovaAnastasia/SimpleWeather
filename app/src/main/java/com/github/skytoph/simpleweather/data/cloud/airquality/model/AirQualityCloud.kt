package com.github.skytoph.simpleweather.data.cloud.airquality.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.data.cloud.airquality.mapper.ToAirQualityDataMapper
import com.github.skytoph.simpleweather.data.model.AirQualityData
import com.squareup.moshi.Json

data class AirQualityCloud(
    @field:Json(name = "list")
    private val airQualityList: List<AirQualityListItemCloud>?

) : Mappable<AirQualityData, ToAirQualityDataMapper> {

    override fun map(mapper: ToAirQualityDataMapper): AirQualityData {
        val airQuality = airQualityList?.get(0)?.map()
        return mapper.map(airQuality?.first ?: 0, airQuality?.second ?: 0)
    }
}

data class AirQualityListItemCloud(
    @field:Json(name = "main")
    private val airQualityIndex: AirQualityIndexCloud,
    @field:Json(name = "dt")
    private val time: Long
) : MappableTo<Pair<Long, Int>> {

    override fun map(): Pair<Long, Int> = Pair(time, airQualityIndex.map())
}


data class AirQualityIndexCloud(
    @field:Json(name = "aqi")
    private val aqi: Int
) : MappableTo<Int> {

    override fun map(): Int = aqi
}
