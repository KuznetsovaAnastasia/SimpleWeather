package com.github.skytoph.simpleweather.domain.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.model.WeatherDomain
import com.github.skytoph.simpleweather.domain.model.WeatherDomain.*
import com.github.skytoph.simpleweather.presentation.WeatherUi
import com.github.skytoph.simpleweather.presentation.WeatherUiComponent

interface WeatherDomainToUiMapper : Mapper<WeatherUi> {

    fun map(
        currentWeather: CurrentWeather,
        indicators: Indicators,
        horizon: Horizon,
        warnings: List<Warning>
    ): WeatherUi
    fun map(message: String): WeatherUi

    class Base(
        private val weatherMapper: CurrentWeatherDomainToUiMapper,
        private val indicatorsMapper: IndicatorsDomainToUiMapper,
        private val horizonMapper: HorizonDomainToUiMapper,
        private val warningsMapper: WarningsDomainToUiMapper,
    ) : WeatherDomainToUiMapper {

        override fun map(
            currentWeather: CurrentWeather,
            indicators: Indicators,
            horizon: Horizon,
            warnings: List<Warning>
        ): WeatherUi = WeatherUi.Success(
            currentWeather.map(weatherMapper),
            warningsMapper.map(warnings),
            indicators.map(indicatorsMapper),
            horizon.map(horizonMapper)
        )

        override fun map(message: String): WeatherUi = WeatherUi.Fail(message)
    }
}
