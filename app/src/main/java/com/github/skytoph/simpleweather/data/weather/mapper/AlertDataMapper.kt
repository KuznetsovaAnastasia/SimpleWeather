package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.AlertData

interface AlertDataMapper : Mapper<AlertData> {

    fun map(name: String, startTime: Long, description: String): AlertData
}
