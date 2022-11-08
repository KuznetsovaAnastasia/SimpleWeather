package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.DailyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.HourlyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.WarningDB
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.ForecastData
import javax.inject.Inject

interface ForecastDBToDataMapper : Mapper<ForecastData> {
    fun map(
        warnings: List<WarningDB>,
        hourly: List<HourlyForecastDB>,
        daily: List<DailyForecastDB>,
    ): ForecastData

    class Base @Inject constructor(
        private val warningsMapper: WarningListDataMapper,
        private val hourlyMapper: HourlyForecastListDataMapper,
        private val dailyMapper: DailyForecastListDataMapper,
        private val hourlyFilter: HourlyForecastFilter,
        private val dailyFilter: DailyForecastFilter,
    ) : ForecastDBToDataMapper {

        override fun map(
            warnings: List<WarningDB>,
            hourly: List<HourlyForecastDB>,
            daily: List<DailyForecastDB>,
        ): ForecastData = ForecastData(warningsMapper.map(warnings),
            hourlyFilter.filter(hourlyMapper.map(hourly)),
            dailyFilter.filter(dailyMapper.map(daily)))
    }
}
