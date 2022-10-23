package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.HorizonData
import javax.inject.Inject

interface HorizonDataMapper : Mapper<HorizonData> {
    fun map(sunrise: Long, sunset: Long, dt: Long, timezoneOffset: Int = 0): HorizonData

    class Base @Inject constructor() : HorizonDataMapper {
        override fun map(sunrise: Long, sunset: Long, dt: Long, timezoneOffset: Int) =
            HorizonData(sunrise + timezoneOffset, sunset + timezoneOffset, dt + timezoneOffset)
    }
}
