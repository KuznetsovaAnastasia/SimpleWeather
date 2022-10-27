package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.AlertData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.ForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import javax.inject.Inject

interface ForecastDataMapper : Mapper<ForecastData> {
    fun <
            W : Mappable<AlertData, AlertDataMapper>,
            H : Mappable<HourlyForecastData, HourlyForecastDataMapper>,
            D : Mappable<DailyForecastData, DailyForecastDataMapper>,
            > map(
        warnings: List<W>,
        hourly: List<H>,
        daily: List<D>,
    ): ForecastData

    class Base @Inject constructor(
        private val warningsMapper: AlertListDataMapper,
        private val hourlyMapper: HourlyForecastListDataMapper,
        private val dailyMapper: DailyForecastListDataMapper,
    ) : ForecastDataMapper {

        override fun <
                W : Mappable<AlertData, AlertDataMapper>,
                H : Mappable<HourlyForecastData, HourlyForecastDataMapper>,
                D : Mappable<DailyForecastData, DailyForecastDataMapper>,
                > map(
            warnings: List<W>,
            hourly: List<H>,
            daily: List<D>,
        ): ForecastData = ForecastData(warningsMapper.map(warnings),
            hourlyMapper.map(hourly),
            dailyMapper.map(daily))
    }
}
