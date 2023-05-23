package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.formatter.LocationFormatter
import com.github.skytoph.simpleweather.domain.weather.model.*
import com.github.skytoph.simpleweather.presentation.weather.model.WeatherUi
import javax.inject.Inject

interface WeatherDomainToUiMapper : Mapper<WeatherUi> {
    fun map(id: String, content: ContentDomain): WeatherUi

    class Base @Inject constructor(
        private val weatherMapper: CurrentWeatherDomainToUiMapper,
        private val indicatorsMapper: IndicatorsDomainToUiMapper,
        private val horizonMapper: HorizonDomainToUiMapper,
        private val forecastMapper: ForecastListUiMapper,
        private val locationLocale: LocationFormatter,
    ) : WeatherDomainToUiMapper {

        override fun map(id: String, content: ContentDomain): WeatherUi {
            return if (content.isOutdated()) {
                val mapper = object : OutdatedWeatherUiMapper {
                    override fun map(location: Map<String, String>) =
                        WeatherUi.Outdated(id, locationLocale.map(location))
                }
                content.map(mapper)
            } else {
                val contentMapper = object : ContentUiMapper {
                    override fun map(
                        currentWeather: CurrentWeatherDomain,
                        indicators: IndicatorsDomain,
                        horizon: HorizonDomain,
                        forecast: ForecastDomain,
                    ) = WeatherUi.Base(
                        id,
                        currentWeather.map(weatherMapper),
                        indicators.map(indicatorsMapper),
                        horizon.map(horizonMapper),
                        forecast.map(forecastMapper)
                    )
                }
                content.map(contentMapper)
            }
        }
    }
}
