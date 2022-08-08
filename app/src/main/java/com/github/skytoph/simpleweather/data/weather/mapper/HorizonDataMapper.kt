package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.HorizonData

interface HorizonDataMapper : Mapper<HorizonData> {
    fun map(sunrise: Long, sunset: Long, currentTime: Long): HorizonData

    class Base : HorizonDataMapper {
        override fun map(sunrise: Long, sunset: Long, currentTime: Long) =
            HorizonData(sunrise, sunset, currentTime)
    }
}
