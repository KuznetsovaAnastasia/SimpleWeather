package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.provider.ResourceProvider
import com.github.skytoph.simpleweather.domain.weather.ErrorType
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
    ): WeatherUi

    fun map(error: ErrorType): WeatherUi

    abstract class Abstract(private val resourceProvider: ResourceProvider) :
        WeatherDomainToUiMapper {

        protected fun errorMessage(error: ErrorType) = resourceProvider.string(
            when (error) {
                ErrorType.NO_CACHED_DATA -> R.string.error_no_cached_data
                ErrorType.SERVICE_UNAVAILABLE -> R.string.error_service_unavailable
                ErrorType.NO_CONNECTION -> R.string.error_no_connection
                else -> R.string.error_general
            }
        )
    }

    class Base @Inject constructor(
        private val weatherMapper: CurrentWeatherDomainToUiMapper,
        private val indicatorsMapper: IndicatorsDomainToUiMapper,
        private val horizonMapper: HorizonDomainToUiMapper,
        private val warningsMapper: WarningsDomainToUiMapper,
        resourceProvider: ResourceProvider,
    ) : WeatherDomainToUiMapper.Abstract(resourceProvider) {

        override fun map(
            id: String,
            currentWeather: CurrentWeather,
            indicators: Indicators,
            horizon: Horizon,
            warnings: List<Warning>,
        ): WeatherUi = WeatherUi.Success(
            id,
            currentWeather.map(weatherMapper),
            warningsMapper.map(warnings),
            indicators.map(indicatorsMapper),
            horizon.map(horizonMapper)
        )

        override fun map(error: ErrorType): WeatherUi = when (error) {
            ErrorType.NO_CACHED_DATA -> WeatherUi.Error(errorMessage(error))
            else -> WeatherUi.Fail(errorMessage(error))
        }
    }
}
