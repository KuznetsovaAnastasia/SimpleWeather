package com.github.skytoph.simpleweather.presentation.weather

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.core.presentation.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.Visibility
import com.github.skytoph.simpleweather.domain.weather.WeatherInteractor
import com.github.skytoph.simpleweather.domain.weather.mapper.WeatherDomainToUiMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val interactor: WeatherInteractor,
    private val progressCommunication: ProgressCommunication.Update,
    private val weatherCommunication: WeatherCommunication,
    private val toUiMapper: WeatherDomainToUiMapper,
) : ViewModel() {

    fun getWeather(placeId: String, favorite: Boolean) {
        progressCommunication.show(Visibility.Visible())
        if (favorite)
            getWeatherCache(placeId, favorite)
        getWeatherCloud(placeId, favorite)
        progressCommunication.show(Visibility.Invisible())
    }

    private fun getWeatherCache(placeId: String, favorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherDomainCached = interactor.getCachedWeather(placeId)
            withContext(Dispatchers.Main) {
                weatherCommunication.show(weatherDomainCached.map(toUiMapper))
            }
        }
    }

    private fun getWeatherCloud(placeId: String, favorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherDomainCloud = interactor.getCloudWeather(placeId, favorite)
//                .also { if (favorite) interactor.cache() }
            withContext(Dispatchers.Main) {
                weatherCommunication.show(weatherDomainCloud.map(toUiMapper))
            }
        }
    }

    fun saveWeather(favorite: Boolean) {
        if (favorite) viewModelScope.launch(Dispatchers.IO) {
            interactor.cache()
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<WeatherUi>) {
        weatherCommunication.observe(owner, observer)
    }
}