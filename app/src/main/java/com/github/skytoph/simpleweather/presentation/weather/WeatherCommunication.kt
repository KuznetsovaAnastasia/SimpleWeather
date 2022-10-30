package com.github.skytoph.simpleweather.presentation.weather

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import com.github.skytoph.simpleweather.presentation.weather.model.WeatherUi
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface WeatherCommunication : Communication.Mutable<WeatherUi> {

    @ViewModelScoped
    class Base @Inject constructor() : Communication.UiUpdate<WeatherUi>(), WeatherCommunication
}
