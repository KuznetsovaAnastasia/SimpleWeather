package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.model.*
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.AlertsDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.*
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import javax.inject.Inject

interface WeatherDBToDataMapper : Mapper<WeatherData> {
    fun map(
        id: String,
        placeId: String,
        priority: Int,
        current: CurrentDB,
        indicators: IndicatorsDB,
        horizon: HorizonDB,
        warnings: List<WarningDB>,
        hourly: List<HourlyForecastDB>,
        daily: List<DailyForecastDB>,
    ): WeatherData

    class Base @Inject constructor(
        private val currentMapper: CurrentWeatherDataMapper,
        private val indicatorsMapper: IndicatorsDataMapper,
        private val horizonMapper: HorizonDataMapper,
        private val warningsMapper: AlertsDataMapper,
        private val hourlyMapper: HourlyForecastListDataMapper,
        private val dailyMapper: DailyForecastListDataMapper,
    ) : WeatherDBToDataMapper {

        override fun map(
            id: String,
            placeId: String,
            priority: Int,
            current: CurrentDB,
            indicators: IndicatorsDB,
            horizon: HorizonDB,
            warnings: List<WarningDB>,
            hourly: List<HourlyForecastDB>,
            daily: List<DailyForecastDB>,
        ) = WeatherData(
            id,
            placeId,
            current.map(currentMapper),
            indicators.map(indicatorsMapper),
            horizon.map(horizonMapper),
            warningsMapper.map(warnings, indicators.precipitationProb),
            hourlyMapper.map(hourly),
            dailyMapper.map(daily),
            priority
        )
    }
}