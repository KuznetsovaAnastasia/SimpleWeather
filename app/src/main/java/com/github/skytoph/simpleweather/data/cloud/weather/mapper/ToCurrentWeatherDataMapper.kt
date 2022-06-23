package com.github.skytoph.simpleweather.data.cloud.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.model.IndicatorsData

interface ToCurrentWeatherDataMapper: Mapper<IndicatorsData> {
    fun map(id: Int, temperature: Double, precipitationProb: Double): IndicatorsData
}