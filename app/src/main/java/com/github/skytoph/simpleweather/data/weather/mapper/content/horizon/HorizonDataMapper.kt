package com.github.skytoph.simpleweather.data.weather.mapper.content.horizon

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import javax.inject.Inject

interface HorizonDataMapper : Mapper<HorizonData> {
    fun map(sunrise: Long, sunset: Long): HorizonData

    class Base @Inject constructor() : HorizonDataMapper {
        override fun map(sunrise: Long, sunset: Long) =
            HorizonData(sunrise, sunset)
    }
}
