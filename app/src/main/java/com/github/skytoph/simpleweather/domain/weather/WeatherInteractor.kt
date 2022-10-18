package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.core.ErrorHandler
import com.github.skytoph.simpleweather.core.exception.CanNotUpdateLocationException
import com.github.skytoph.simpleweather.data.weather.mapper.WeatherDataToDomainMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.domain.weather.mapper.WeatherDomainToUiMapper
import com.github.skytoph.simpleweather.presentation.weather.WeatherUi
import javax.inject.Inject

interface WeatherInteractor {
    suspend fun getCachedWeather(id: String): WeatherUi
    suspend fun getCloudWeather(id: String, favorite: Boolean): WeatherUi
    suspend fun updateLocation(id: String): WeatherUi

    class Base @Inject constructor(
        private val repository: WeatherRepository.Mutable,
        private val domainMapper: WeatherDataToDomainMapper,
        private val uiMapper: WeatherDomainToUiMapper,
        private val errorHandler: ErrorHandler,
    ) : WeatherInteractor {

        override suspend fun getCloudWeather(id: String, favorite: Boolean): WeatherUi = try {
            val weather =
                if (favorite) repository.updateCloudWeather(id)
                else repository.getCloudWeather(id)
            weather.mapToUi()
        } catch (exception: Exception) {
            errorHandler.handle(exception)
            WeatherUi.Fail
        }

        override suspend fun getCachedWeather(id: String): WeatherUi = try {
            repository.getCachedWeather(id).mapToUi()
        } catch (exception: Exception) {
            WeatherUi.Fail
        }

        override suspend fun updateLocation(id: String): WeatherUi = try {
            repository.updateLocationName(id).mapToUi()
        } catch (exception: Exception) {
            errorHandler.handle(CanNotUpdateLocationException())
            WeatherUi.Fail
        }

        private fun WeatherData.mapToUi() = this.map(domainMapper).map(uiMapper)
    }
}
