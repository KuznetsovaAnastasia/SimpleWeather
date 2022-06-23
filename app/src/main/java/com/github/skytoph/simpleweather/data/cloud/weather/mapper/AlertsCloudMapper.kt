package com.github.skytoph.simpleweather.data.cloud.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.cloud.weather.model.AlertCloud
import com.github.skytoph.simpleweather.data.model.AlertData

interface AlertsCloudMapper : Mapper<List<AlertData>> {

    fun map(alerts: List<AlertCloud>, pop: Double): List<AlertData>

    class Base: AlertsCloudMapper {
        override fun map(alerts: List<AlertCloud>, pop: Double): List<AlertData> {
            val mapper = object : AlertCloudMapper {
                override fun map(name: String, startTime: Long) = AlertData(name, startTime, pop)
            }
            return alerts.map { it.map(mapper) }
        }
    }
}