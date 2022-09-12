package com.github.skytoph.simpleweather.presentation.weather

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.core.presentation.communication.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.view.visibility.Visibility
import com.github.skytoph.simpleweather.domain.weather.WeatherInteractor
import com.github.skytoph.simpleweather.presentation.RefreshCommunication
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
    private val refreshCommunication: RefreshCommunication.Observe,
) : ViewModel() {

    fun getWeather(placeId: String, favorite: Boolean) {
        progressCommunication.show(Visibility.Visible())
        if (favorite) getWeatherCache(placeId)
        getWeatherCloud(placeId, favorite)
    }

    private fun getWeatherCloud(placeId: String, favorite: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getCloudWeather(placeId, favorite, showWeather())
        }

    private fun getWeatherCache(placeId: String) = viewModelScope.launch(Dispatchers.IO) {
        interactor.getCachedWeather(placeId, showWeather())
    }

    private fun showWeather(): suspend (WeatherUi) -> Unit = {
        withContext(Dispatchers.Main) {
            weatherCommunication.show(it)
            progressCommunication.show(Visibility.Invisible())
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<WeatherUi>) {
        weatherCommunication.observe(owner, observer)
    }

    fun observeRefresh(owner: LifecycleOwner, observer: Observer<Boolean>) {
        refreshCommunication.observe(owner, observer)
    }

    fun refresh(placeId: String, favorite: Boolean) {
        if (favorite) getWeatherCache(placeId)
    }
}