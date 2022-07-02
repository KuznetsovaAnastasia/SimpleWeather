package com.github.skytoph.simpleweather.presentation.weather

import androidx.lifecycle.*
import com.github.skytoph.simpleweather.core.presentation.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.Visibility
import com.github.skytoph.simpleweather.domain.weather.mapper.WeatherDomainToUiMapper
import com.github.skytoph.simpleweather.domain.weather.WeatherInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val interactor: WeatherInteractor,
    private val progressCommunication: ProgressCommunication.Update,
    private val weatherCommunication: WeatherCommunication,
    private val toUiMapper: WeatherDomainToUiMapper,
) : ViewModel() {

    fun getWeather(
        lat: Double = 47.70,
        lng: Double = 32.51,
    ) {
        progressCommunication.show(Visibility.Visible())
        viewModelScope.launch(Dispatchers.IO) {
            val weatherDomain = interactor.getWeather(lat, lng)

            withContext(Dispatchers.Main) {
                progressCommunication.show(Visibility.Invisible())
                weatherCommunication.show(weatherDomain.map(toUiMapper))
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<WeatherUi>) {
        weatherCommunication.observe(owner, observer)
    }
}