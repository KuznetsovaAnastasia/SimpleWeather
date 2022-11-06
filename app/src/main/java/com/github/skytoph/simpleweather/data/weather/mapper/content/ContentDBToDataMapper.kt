package com.github.skytoph.simpleweather.data.weather.mapper.content

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.HorizonDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.IndicatorsDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.mapper.content.current.ForecastToCurrentDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.FindForecastMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.ForecastDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.horizon.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.indicators.IndicatorsDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.ContentData
import javax.inject.Inject

interface ContentDBToDataMapper : Mapper<ContentData> {
    fun map(
        location: String,
        indicators: IndicatorsDB,
        horizon: HorizonDB,
        forecast: ForecastDB,
    ): ContentData

    class Base @Inject constructor(
        private val currentMapper: ForecastToCurrentDataMapper,
        private val indicatorsMapper: IndicatorsDataMapper,
        private val horizonMapper: HorizonDataMapper,
        private val forecastMapper: ForecastDBToDataMapper,
        private val findForecast: FindForecastMapper,
    ) : ContentDBToDataMapper {

        override fun map(
            location: String,
            indicators: IndicatorsDB,
            horizon: HorizonDB,
            forecast: ForecastDB,
        ): ContentData = ContentData(currentMapper.map(forecast.map(findForecast), location),
            indicators.map(indicatorsMapper),
            horizon.map(horizonMapper),
            forecast.map(forecastMapper))
    }
}
