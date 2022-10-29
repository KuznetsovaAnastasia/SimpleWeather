package com.github.skytoph.simpleweather.data.weather.cloud.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.WarningDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.WarningData
import com.squareup.moshi.Json

data class AlertCloud(
    @field:Json(name = "event")
    private val name: String,

    @field:Json(name = "start")
    private val startTime: Long,

    @field:Json(name = "description")
    private val description: String,
) : Mappable<WarningData, WarningDataMapper> {

    override fun map(mapper: WarningDataMapper): WarningData = mapper.map(name, startTime, description)
}
