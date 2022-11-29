package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.time.RoundedTime
import com.github.skytoph.simpleweather.core.util.time.TimeSeconds
import com.github.skytoph.simpleweather.data.weather.cache.model.content.HorizonDB
import com.github.skytoph.simpleweather.data.weather.mapper.content.horizon.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import javax.inject.Inject

interface DailyForecastDataMapper : Mapper<DailyForecastData> {

    fun map(
        time: Long,
        tempMin: Double,
        tempMax: Double,
        weatherId: Int,
        pop: Double,
        uvi: Double,
        horizon: HorizonDB,
    ): DailyForecastData

    fun map(
        time: Long,
        temp: Pair<Double, Double>,
        weatherId: Int,
        pop: Double,
        uvi: Double,
        sunrise: Long,
        sunset: Long,
    ): DailyForecastData

    class Base @Inject constructor(private val horizonMapper: HorizonDataMapper) :
        DailyForecastDataMapper {

        override fun map(
            time: Long,
            tempMin: Double,
            tempMax: Double,
            weatherId: Int,
            pop: Double,
            uvi: Double,
            horizon: HorizonDB,
        ) = DailyForecastData(RoundedTime(TimeSeconds.Base(time)).roundToDay(),
            Pair(tempMin, tempMax),
            weatherId,
            pop,
            uvi,
            horizon.map(horizonMapper))

        override fun map(
            time: Long,
            temp: Pair<Double, Double>,
            weatherId: Int,
            pop: Double,
            uvi: Double,
            sunrise: Long,
            sunset: Long,
        ) = DailyForecastData(RoundedTime(TimeSeconds.Base(time)).roundToDay(),
            temp,
            weatherId,
            pop,
            uvi,
            horizonMapper.map(sunrise, sunset))
    }
}