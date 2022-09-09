package com.github.skytoph.simpleweather.presentation.weather

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.core.presentation.communication.MessageCommunication
import com.github.skytoph.simpleweather.core.presentation.communication.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.view.visibility.Visibility
import com.github.skytoph.simpleweather.domain.weather.WeatherInteractor
import com.github.skytoph.simpleweather.domain.weather.mapper.WeatherDomainToUiMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
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
    private val messageCommunication: MessageCommunication.Update,
    private val refreshCommunication: RefreshCommunication.Observe,
    private val toUiMapper: WeatherDomainToUiMapper,
) : ViewModel() {

    fun getWeather(placeId: String, favorite: Boolean) {
        progressCommunication.show(Visibility.Visible())
        if (favorite) getWeatherCache(placeId)
        getWeatherCloud(placeId, favorite)
    }

    fun refresh(placeId: String, favorite: Boolean) {
        if(favorite) getWeatherCache(placeId)
    }

    private fun get(fetch: suspend () -> WeatherDomain) = viewModelScope.launch(Dispatchers.IO) {
        val weather = fetch.invoke()
        withContext(Dispatchers.Main) {
            val weatherUi = weather.map(toUiMapper).also { it.show(messageCommunication) }
            weatherCommunication.show(weatherUi)
            progressCommunication.show(Visibility.Invisible())
        }
    }

    private fun getWeatherCloud(placeId: String, favorite: Boolean) =
        get { interactor.getCloudWeather(placeId, favorite) }

    private fun getWeatherCache(placeId: String) =
        get { interactor.getCachedWeather(placeId) }

    fun observe(owner: LifecycleOwner, observer: Observer<WeatherUi>) {
        weatherCommunication.observe(owner, observer)
    }

    fun observeRefresh(owner: LifecycleOwner, observer: Observer<Boolean>) {
        refreshCommunication.observe(owner, observer)
    }
}