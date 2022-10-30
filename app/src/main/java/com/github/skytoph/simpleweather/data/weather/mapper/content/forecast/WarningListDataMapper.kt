package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.WarningData
import javax.inject.Inject

interface WarningListDataMapper : Mapper<List<WarningData>> {

    fun <T : Mappable<WarningData, WarningDataMapper>>
            map(alerts: List<T>): List<WarningData>

    class Base @Inject constructor(
        private val mapper: WarningDataMapper,
        private val filter: WarningsDataFilter,
        ) : WarningListDataMapper {

        override fun <T : Mappable<WarningData, WarningDataMapper>> map(alerts: List<T>): List<WarningData> =
            filter.filter(alerts.map { it.map(mapper) })
    }
}