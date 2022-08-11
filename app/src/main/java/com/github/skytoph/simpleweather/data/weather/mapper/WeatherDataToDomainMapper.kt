package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.exception.NoCachedDataException
import com.github.skytoph.simpleweather.data.weather.model.*
import com.github.skytoph.simpleweather.domain.weather.ErrorType
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

interface WeatherDataToDomainMapper : Mapper<WeatherDomain> {

    fun map(
        id: String,
        currentWeatherData: CurrentWeatherData,
        indicatorsData: IndicatorsData,
        horizonData: HorizonData,
        alertData: List<AlertData>,
    ): WeatherDomain

    fun map(e: Exception): WeatherDomain

    abstract class Abstract : WeatherDataToDomainMapper {

        protected fun errorType(e: Exception) = when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            is NoCachedDataException -> ErrorType.NO_CACHED_DATA
            else -> ErrorType.GENERIC_ERROR
        }
    }

    class Base @Inject constructor(
        private val weatherMapper: CurrentWeatherDataToDomainMapper,
        private val indicatorsMapper: IndicatorsDataToDomainMapper,
        private val horizonMapper: HorizonDataToDomainMapper,
        private val warningsMapper: WarningsDataToDomainMapper,
    ) : WeatherDataToDomainMapper.Abstract() {

        override fun map(
            id: String,
            currentWeatherData: CurrentWeatherData,
            indicatorsData: IndicatorsData,
            horizonData: HorizonData,
            alertData: List<AlertData>,
        ): WeatherDomain = WeatherDomain.Base(
            id,
            currentWeatherData.map(weatherMapper),
            indicatorsData.map(indicatorsMapper),
            horizonData.map(horizonMapper),
            warningsMapper.map(alertData)
        )

        override fun map(e: Exception): WeatherDomain = WeatherDomain.Fail(errorType(e))

    }
}