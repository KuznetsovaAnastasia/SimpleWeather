package com.github.skytoph.simpleweather.data.weather.cloud.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.AlertDataMapper
import com.github.skytoph.simpleweather.data.weather.model.AlertData
import com.squareup.moshi.Json

data class AlertCloud(
    @field:Json(name = "event")
    private val name: String,

    @field:Json(name = "start")
    private val startTime: Long,
) : Mappable<AlertData, AlertDataMapper> {

    override fun map(mapper: AlertDataMapper): AlertData = mapper.map(name, startTime)
}
