package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.AlertData
import javax.inject.Inject

interface AlertListDataMapper : Mapper<List<AlertData>> {

    fun <T : Mappable<AlertData, AlertDataMapper>>
            map(alerts: List<T>): List<AlertData>

    class Base @Inject constructor(private val mapper: AlertDataMapper) : AlertListDataMapper {

        override fun <T : Mappable<AlertData, AlertDataMapper>> map(alerts: List<T>): List<AlertData> =
            alerts.map { it.map(mapper) }
    }
}