package com.github.skytoph.simpleweather.data.weather.cloud.mapper

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.mapper.AlertDataMapper
import com.github.skytoph.simpleweather.data.weather.model.AlertData
import javax.inject.Inject

interface AlertsDataMapper : Mapper<List<AlertData>> {

    fun <T : Mappable<AlertData, AlertDataMapper>>
            map(alerts: List<T>, pop: Double): List<AlertData>

    class Base @Inject constructor() : AlertsDataMapper {

        override fun <T : Mappable<AlertData, AlertDataMapper>>
                map(alerts: List<T>, pop: Double): List<AlertData> {
            val mapper = object : AlertDataMapper {
                override fun map(name: String, startTime: Long, description: String) =
                    AlertData(name, startTime, pop, description)
            }
            return alerts.map { it.map(mapper) }
        }
    }
}