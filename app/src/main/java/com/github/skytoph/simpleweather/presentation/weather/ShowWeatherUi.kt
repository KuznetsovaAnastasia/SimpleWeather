package com.github.skytoph.simpleweather.presentation.weather

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.skytoph.simpleweather.core.presentation.view.IndicatorsView
import com.github.skytoph.simpleweather.core.presentation.view.LocationView
import com.github.skytoph.simpleweather.core.presentation.view.SunriseSunsetView

abstract class ShowWeatherUi {

    open fun show(
        locationView: LocationView,
        indicatorsView: IndicatorsView,
        sunriseSunsetView: SunriseSunsetView,
        recyclerView: RecyclerView,
        messageView: TextView,
        submitLists: (List<WeatherUiComponent.Warning>, List<WeatherUiComponent.HourlyForecast>, List<WeatherUiComponent.DailyForecast>) -> Unit,
    ) {
        show(locationView,
            indicatorsView,
            sunriseSunsetView,
            recyclerView,
            submitLists)
    }

    open fun show(
        locationView: LocationView,
        indicatorsView: IndicatorsView,
        sunriseSunsetView: SunriseSunsetView,
        recyclerView: RecyclerView,
        submitLists: (List<WeatherUiComponent.Warning>, List<WeatherUiComponent.HourlyForecast>, List<WeatherUiComponent.DailyForecast>) -> Unit,
    ) = Unit
}
