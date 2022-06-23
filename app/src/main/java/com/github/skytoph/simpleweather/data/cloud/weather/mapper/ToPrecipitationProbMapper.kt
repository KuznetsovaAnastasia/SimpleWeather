package com.github.skytoph.simpleweather.data.cloud.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper

interface ToPrecipitationProbMapper: Mapper<Double> {
    fun map(precipitationProb: Double): Double
}
