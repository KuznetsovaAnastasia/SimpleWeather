package com.github.skytoph.simpleweather.data.airquality.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.model.AirQualityData

interface ToAirQualityDataMapper: Mapper<AirQualityData> {

    fun map(time: Long, index: Int): AirQualityData

    class Base: ToAirQualityDataMapper {

        override fun map(time: Long, index: Int) = AirQualityData(time, index)
    }
}
