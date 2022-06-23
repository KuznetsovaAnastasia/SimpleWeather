package com.github.skytoph.simpleweather.data.cloud.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.cloud.weather.mapper.AlertCloudMapper
import com.github.skytoph.simpleweather.data.model.AlertData
import com.squareup.moshi.Json

data class AlertCloud(
    @field:Json(name="event")
    private val name: String,

    @field:Json(name="start")
    private val startTime: Long
): Mappable<AlertData, AlertCloudMapper>{

    override fun map(mapper: AlertCloudMapper): AlertData = mapper.map(name, startTime)
}
