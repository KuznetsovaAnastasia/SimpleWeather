package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.model.HorizonDB
import javax.inject.Inject

interface HorizonDBMapper : Mapper<HorizonDB> {
    fun map(sunrise: Long, sunset: Long, currentTime: Long): HorizonDB

    class Base @Inject constructor() : HorizonDBMapper {
        override fun map(
            sunrise: Long,
            sunset: Long,
            currentTime: Long,
        ): HorizonDB = HorizonDB().apply {
            this.sunrise = sunrise
            this.sunset = sunset
            this.currentTime = currentTime
        }
    }
}
