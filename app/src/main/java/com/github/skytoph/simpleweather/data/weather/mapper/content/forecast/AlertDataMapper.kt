package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.AlertData
import javax.inject.Inject

interface AlertDataMapper : Mapper<AlertData> {

    fun map(name: String, startTime: Long, description: String): AlertData

    class Base @Inject constructor() : AlertDataMapper {

        override fun map(name: String, startTime: Long, description: String) =
            AlertData(name, startTime, description)
    }
}
