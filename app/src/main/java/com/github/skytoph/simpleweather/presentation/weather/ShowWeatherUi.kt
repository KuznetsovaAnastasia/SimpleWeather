package com.github.skytoph.simpleweather.presentation.weather

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.skytoph.simpleweather.core.presentation.communication.MessageCommunication
import com.github.skytoph.simpleweather.core.presentation.view.IndicatorsView
import com.github.skytoph.simpleweather.core.presentation.view.LocationView
import com.github.skytoph.simpleweather.core.presentation.view.SunriseSunsetView
import com.github.skytoph.simpleweather.presentation.weather.adapter.forecast.HourlyForecastAdapter
import com.github.skytoph.simpleweather.presentation.weather.adapter.warning.WarningAdapter

abstract class ShowWeatherUi {

    open fun show(
        locationView: LocationView,
        indicatorsView: IndicatorsView,
        sunriseSunsetView: SunriseSunsetView,
        warningAdapter: WarningAdapter,
        hourlyAdapter: HourlyForecastAdapter,
        recyclerView: RecyclerView,
        messageView: TextView,
    ) {
        show(locationView, indicatorsView, sunriseSunsetView, warningAdapter, hourlyAdapter, recyclerView)
        show(messageView)
    }

    open fun show(messageView: TextView) = Unit

    open fun show(
        locationView: LocationView,
        indicatorsView: IndicatorsView,
        sunriseSunsetView: SunriseSunsetView,
        warningAdapter: WarningAdapter,
        hourlyAdapter: HourlyForecastAdapter,
        recyclerView: RecyclerView,
    ) = Unit

    open fun show(messageCommunication: MessageCommunication.Update) = Unit
}
