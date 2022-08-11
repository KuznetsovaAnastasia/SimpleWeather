package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.CurrentDB
import com.github.skytoph.simpleweather.data.weather.cache.HorizonDB
import com.github.skytoph.simpleweather.data.weather.cache.IndicatorsDB
import com.github.skytoph.simpleweather.data.weather.mapper.CurrentWeatherDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.IndicatorsDataMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import javax.inject.Inject

interface WeatherDBToDataMapper : Mapper<WeatherData> {
    fun map(
        id: String,
        current: CurrentDB,
        indicators: IndicatorsDB,
        horizon: HorizonDB,
    ): WeatherData

    class Base @Inject constructor(
        private val currentMapper: CurrentWeatherDataMapper,
        private val indicatorsMapper: IndicatorsDataMapper,
        private val horizonMapper: HorizonDataMapper,
    ) : WeatherDBToDataMapper {

        override fun map(
            id: String,
            current: CurrentDB,
            indicators: IndicatorsDB,
            horizon: HorizonDB,
        ) = WeatherData.Info(
            id,
            current.map(currentMapper),
            indicators.map(indicatorsMapper),
            horizon.map(horizonMapper),
            emptyList()
        )
    }
}