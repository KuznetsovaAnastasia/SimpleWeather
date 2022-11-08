package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.core.ErrorHandler
import com.github.skytoph.simpleweather.data.weather.mapper.WeatherDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.WeatherDomainToUiMapper
import com.github.skytoph.simpleweather.presentation.weather.model.WeatherUi
import javax.inject.Inject

interface WeatherInteractor {
    suspend fun getWeather(id: String, favorite: Boolean): WeatherUi

    class Base @Inject constructor(
        private val repository: WeatherRepository.Update,
        private val domainMapper: WeatherDataToDomainMapper,
        private val uiMapper: WeatherDomainToUiMapper,
        private val errorHandler: ErrorHandler,
    ) : WeatherInteractor {

        override suspend fun getWeather(id: String, favorite: Boolean): WeatherUi = try {
            val weather = if (favorite) repository.getCachedWeather(id)
            else repository.getCloudWeather(id)
            weather.map(domainMapper).map(uiMapper)
        } catch (exception: Exception) {
            errorHandler.handle(exception)
            WeatherUi.Fail
        }
    }
}