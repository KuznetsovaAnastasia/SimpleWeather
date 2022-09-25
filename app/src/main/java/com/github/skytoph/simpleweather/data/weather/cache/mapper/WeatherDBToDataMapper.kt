package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.*
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.AlertsDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.CurrentWeatherDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.HourlyForecastListDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.IndicatorsDataMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import javax.inject.Inject

interface WeatherDBToDataMapper : Mapper<WeatherData> {
    fun map(
        id: String,
        current: CurrentDB,
        indicators: IndicatorsDB,
        horizon: HorizonDB,
        warnings: List<WarningDB>,
        hourly: List<HourlyForecastDB>,
    ): WeatherData

    class Base @Inject constructor(
        private val currentMapper: CurrentWeatherDataMapper,
        private val indicatorsMapper: IndicatorsDataMapper,
        private val horizonMapper: HorizonDataMapper,
        private val warningsMapper: AlertsDataMapper,
        private val hourlyMapper: HourlyForecastListDataMapper,
    ) : WeatherDBToDataMapper {

        override fun map(
            id: String,
            current: CurrentDB,
            indicators: IndicatorsDB,
            horizon: HorizonDB,
            warnings: List<WarningDB>,
            hourly: List<HourlyForecastDB>,
        ) = WeatherData.Info(
            id,
            current.map(currentMapper),
            indicators.map(indicatorsMapper),
            horizon.map(horizonMapper),
            warningsMapper.map(warnings, indicators.precipitationProb),
            hourlyMapper.map(hourly)
        )
    }
}