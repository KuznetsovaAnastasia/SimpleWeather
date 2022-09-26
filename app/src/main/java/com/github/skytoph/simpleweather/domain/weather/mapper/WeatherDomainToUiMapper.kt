package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.ErrorType
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.presentation.view.horizon.ResourceProvider
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain.*
import com.github.skytoph.simpleweather.presentation.weather.WeatherUi
import javax.inject.Inject

interface WeatherDomainToUiMapper : Mapper<WeatherUi> {

    fun map(
        id: String,
        currentWeather: CurrentWeather,
        indicators: Indicators,
        horizon: Horizon,
        warnings: List<Warning>,
        hourly: List<HourlyForecast>,
        daily: List<DailyForecast>,
    ): WeatherUi

    fun map(error: ErrorType): WeatherUi

    class Base @Inject constructor(
        private val weatherMapper: CurrentWeatherDomainToUiMapper,
        private val indicatorsMapper: IndicatorsDomainToUiMapper,
        private val horizonMapper: HorizonDomainToUiMapper,
        private val warningsMapper: WarningsDomainToUiMapper,
        private val hourlyMapper: HourlyForecastListToUiMapper,
        private val dailyMapper: DailyForecastListToUiMapper,
        resourceProvider: ResourceProvider,
    ) : WeatherDomainToUiMapper, Mapper.ToUi<WeatherUi>(resourceProvider) {

        override fun map(
            id: String,
            currentWeather: CurrentWeather,
            indicators: Indicators,
            horizon: Horizon,
            warnings: List<Warning>,
            hourly: List<HourlyForecast>,
            daily: List<DailyForecast>,
        ): WeatherUi = WeatherUi.Success(
            id,
            currentWeather.map(weatherMapper),
            warningsMapper.map(warnings),
            indicators.map(indicatorsMapper),
            hourlyMapper.map(hourly),
            dailyMapper.map(daily),
            horizon.map(horizonMapper),
        )

        override fun map(error: ErrorType): WeatherUi {
            val message = errorMessage(error)
            return when (error) {
                ErrorType.NO_CACHED_DATA -> WeatherUi.Error(message)
                else -> WeatherUi.Fail
            }
        }
    }
}
