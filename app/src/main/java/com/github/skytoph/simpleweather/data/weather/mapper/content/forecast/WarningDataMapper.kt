package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.WarningData
import javax.inject.Inject

interface WarningDataMapper : Mapper<WarningData> {

    fun map(name: String, startTime: Long, endTime: Long, description: String): WarningData

    class Base @Inject constructor() : WarningDataMapper {

        override fun map(name: String, startTime: Long, endTime: Long, description: String) =
            WarningData(name, startTime, endTime, description)
    }
}
