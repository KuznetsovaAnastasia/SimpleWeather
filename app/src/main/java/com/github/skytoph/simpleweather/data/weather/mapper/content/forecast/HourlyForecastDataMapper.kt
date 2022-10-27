package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData

interface HourlyForecastDataMapper : Mapper<HourlyForecastData> {

    fun map(time: Long, temp: Double, weatherId: Int, pop: Double): HourlyForecastData

}
