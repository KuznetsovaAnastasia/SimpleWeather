package com.github.skytoph.simpleweather.data.model

import com.github.skytoph.simpleweather.core.MappableTo

data class AirQualityData(
    private val time: Long,
    private val qualityIndex: Int
) :
    MappableTo<Int> {

    override fun map(): Int = qualityIndex
}
