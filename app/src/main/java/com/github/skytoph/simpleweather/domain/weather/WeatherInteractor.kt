package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.core.ErrorHandler
import com.github.skytoph.simpleweather.data.weather.mapper.WeatherDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.WeatherDomainToUiMapper
import com.github.skytoph.simpleweather.presentation.weather.WeatherUi
import javax.inject.Inject

interface WeatherInteractor {
    suspend fun getCachedWeather(id: String, showResult: suspend (WeatherUi) -> Unit)
    suspend fun getCloudWeather(
        id: String,
        favorite: Boolean,
        showResult: suspend (WeatherUi) -> Unit,
    )

    class Base @Inject constructor(
        private val repository: WeatherRepository.Mutable,
        private val domainMapper: WeatherDataToDomainMapper,
        private val uiMapper: WeatherDomainToUiMapper,
        private val errorHandler: ErrorHandler,
    ) : WeatherInteractor {

        override suspend fun getCloudWeather(
            id: String,
            favorite: Boolean,
            showResult: suspend (WeatherUi) -> Unit,
        ) = try {
            val weather =
                if (favorite) repository.updateCloudWeather(id)
                else repository.getCloudWeather(id)
            showResult.invoke(weather.map(domainMapper).map(uiMapper))
        } catch (exception: Exception) {
            errorHandler.handle(exception)
        }

        override suspend fun getCachedWeather(id: String, showResult: suspend (WeatherUi) -> Unit) =
            showResult.invoke(repository.getCachedWeather(id).map(domainMapper).map(uiMapper))
    }
}
