package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.model.AlertData

interface AlertCloudMapper: Mapper<AlertData> {

    fun map(name: String, startTime: Long): AlertData
}
