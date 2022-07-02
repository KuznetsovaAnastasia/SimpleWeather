package com.github.skytoph.simpleweather.presentation.weather

import com.github.skytoph.simpleweather.core.presentation.communication.Communication

interface WeatherCommunication: Communication.Mutable<WeatherUi>{

    class Base: Communication.UiUpdate<WeatherUi>(), WeatherCommunication
}
