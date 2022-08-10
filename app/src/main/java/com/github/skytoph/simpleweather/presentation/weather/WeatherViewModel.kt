package com.github.skytoph.simpleweather.presentation.weather

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.core.presentation.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.Visibility
import com.github.skytoph.simpleweather.domain.weather.WeatherInteractor
import com.github.skytoph.simpleweather.domain.weather.mapper.WeatherDomainToUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
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
            withContext(Dispatchers.Main) {
                weatherCommunication.show(weatherDomainCloud.map(toUiMapper))
            }
        }
    }

    fun saveWeather(favorite: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        interactor.cache(favorite)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<WeatherUi>) {
        weatherCommunication.observe(owner, observer)
    }
}