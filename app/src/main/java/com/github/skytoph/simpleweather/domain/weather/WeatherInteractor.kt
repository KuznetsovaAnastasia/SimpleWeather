package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.core.ErrorHandler
import com.github.skytoph.simpleweather.core.exception.CanNotUpdateLocationException
import com.github.skytoph.simpleweather.data.weather.mapper.WeatherDataToDomainMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.domain.weather.mapper.WeatherDomainToUiMapper
import com.github.skytoph.simpleweather.presentation.weather.model.WeatherUi
import javax.inject.Inject

interface WeatherInteractor {
    suspend fun getCachedWeather(id: String): WeatherUi
    suspend fun getWeather(id: String, favorite: Boolean): WeatherUi

    class Base @Inject constructor(
        private val repository: WeatherRepository.Mutable,
        private val refreshLocation: RefreshLocation.SaveRefreshed,
        private val domainMapper: WeatherDataToDomainMapper,
        private val uiMapper: WeatherDomainToUiMapper,
        private val errorHandler: ErrorHandler,
    ) : WeatherInteractor {

        override suspend fun getWeather(id: String, favorite: Boolean): WeatherUi = try {
            if (favorite) getCachedWeather(id)
            else repository.getCloudWeather(id).mapToUi()
        } catch (exception: Exception) {
            errorHandler.handle(exception)
            WeatherUi.Fail
        }

        override suspend fun getCachedWeather(id: String): WeatherUi = try {
            if (refreshLocation.intentionSaved(id))
                repository.updateLocationName(id).also { it.saveState(refreshLocation) }.mapToUi()
            else
                repository.getCachedWeather(id).mapToUi()
        } catch (exception: CanNotUpdateLocationException) {
            errorHandler.handle(exception)
            repository.getCachedWeather(id).mapToUi()
        } catch (exception: Exception) {
            WeatherUi.Fail
        }

        private fun WeatherData.mapToUi() = this.map(domainMapper).map(uiMapper)
    }
}