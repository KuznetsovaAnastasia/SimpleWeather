package com.github.skytoph.simpleweather.domain.addlocation

import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface AddLocationInteractor {
    suspend fun save()

    @ViewModelScoped
    class Base @Inject constructor(private val weatherRepository: WeatherRepository.Mutable) :
        AddLocationInteractor {

        override suspend fun save() = weatherRepository.saveWeather()
    }
}
