package com.github.skytoph.simpleweather.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.data.AirRepository
import com.github.skytoph.simpleweather.data.LocationRepository
import com.github.skytoph.simpleweather.data.WeatherRepository
import com.github.skytoph.simpleweather.data.mapper.WeatherDomainMapper
import com.github.skytoph.simpleweather.domain.mapper.WeatherDomainToUiMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val airRepository: AirRepository,
    private val locationRepository: LocationRepository,
    private val communication: StateCommunication,
    private val toDomainMapper: WeatherDomainMapper,
    private val toUiMapper: WeatherDomainToUiMapper,
) : ViewModel() {

    fun getWeather(
        //saint petersburg coordinates
        lat: Double = 47.70,
        lng: Double = 32.51
    ) = viewModelScope.launch(Dispatchers.IO) {
//        val lat = 47.70
//        val lng = 32.51

        val weatherData = weatherRepository.getWeather(lat, lng)
        val airData = airRepository.getAirQuality(lat, lng)
        val locationData = locationRepository.getLocation(lat, lng)

        val weatherDomain = toDomainMapper.map(weatherData, airData, locationData)
        val weatherUi = weatherDomain.map(toUiMapper)

        withContext(Dispatchers.Main) {
            weatherUi.show(communication)
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        communication.observe(owner, observer)
    }
}