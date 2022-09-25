package com.github.skytoph.simpleweather.presentation.weather

import android.widget.TextView
import com.github.skytoph.simpleweather.core.presentation.communication.MessageCommunication
import com.github.skytoph.simpleweather.core.presentation.view.visibility.Visibility
import com.github.skytoph.simpleweather.core.presentation.view.IndicatorsView
import com.github.skytoph.simpleweather.core.presentation.view.LocationView
import com.github.skytoph.simpleweather.core.presentation.view.SunriseSunsetView
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent.*
import com.github.skytoph.simpleweather.presentation.weather.adapter.WarningAdapter

sealed class WeatherUi : ShowWeatherUi() {

    data class Success(
        private val id: String,
        private val current: Current,
        private val warnings: List<Warning>,
        private val indicator: Indicator,
        private val hourly: List<HourlyForecast>,
        private val horizon: Horizon,
    ) : WeatherUi() {
        override fun show(
            locationView: LocationView,
            indicatorsView: IndicatorsView,
            sunriseSunsetView: SunriseSunsetView,
            warningAdapter: WarningAdapter,
            hourlyAdapter: HourlyForecastAdapter,
        ) {
            locationView.show(current)
            indicatorsView.show(indicator)
            sunriseSunsetView.show(horizon)
            warningAdapter.submitList(warnings)
            hourlyAdapter.submitList(hourly)
        }

        override fun show(messageView: TextView) = Visibility.Gone().apply(messageView)

    }

    object Fail : WeatherUi()

    data class Error(private val message: String) : WeatherUi() {
        override fun show(
            locationView: LocationView,
            indicatorsView: IndicatorsView,
            sunriseSunsetView: SunriseSunsetView,
            warningAdapter: WarningAdapter,
            hourlyAdapter: HourlyForecastAdapter,
        ) = Visibility.Gone().run {
            apply(locationView)
            apply(indicatorsView)
            apply(sunriseSunsetView)
            warningAdapter.submitList(emptyList())
        }

        override fun show(messageView: TextView) {
            Visibility.Visible().apply(messageView)
            messageView.text = message
        }
    }
}