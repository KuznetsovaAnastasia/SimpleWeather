package com.github.skytoph.simpleweather.domain.addlocation

import android.util.Log
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface AddLocationInteractor {
    suspend fun save()

    @ViewModelScoped
    class Base @Inject constructor(private val repository: WeatherRepository.Save) :
        AddLocationInteractor {

        override suspend fun save() {
            try {
                repository.saveWeather()
            } catch (e: Exception) {
                Log.e("ErrorTag", e.stackTraceToString())
            }
        }
    }
}