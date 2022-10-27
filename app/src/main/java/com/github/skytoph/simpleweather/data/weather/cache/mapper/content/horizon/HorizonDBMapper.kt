package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.horizon

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.HorizonDB
import javax.inject.Inject

interface HorizonDBMapper : Mapper<HorizonDB> {
    fun map(sunrise: Long, sunset: Long): HorizonDB

    class Base @Inject constructor() : HorizonDBMapper {
        override fun map(
            sunrise: Long,
            sunset: Long,
        ): HorizonDB = HorizonDB().apply {
            this.sunrise = sunrise
            this.sunset = sunset
        }
    }
}
