package com.github.skytoph.simpleweather.presentation.weather

import android.widget.TextView
import com.github.skytoph.simpleweather.core.presentation.Visibility
import com.github.skytoph.simpleweather.core.presentation.view.IndicatorsView
import com.github.skytoph.simpleweather.core.presentation.view.LocationView
import com.github.skytoph.simpleweather.core.presentation.view.SunriseSunsetView
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent.*
import com.github.skytoph.simpleweather.presentation.weather.adapter.WarningAdapter

sealed class WeatherUi : ShowWeatherUi() {

    data class Fail(private val message: String) : WeatherUi() {
        override fun show(
            locationView: LocationView,
            indicatorsView: IndicatorsView,
            sunriseSunsetView: SunriseSunsetView,
            warningAdapter: WarningAdapter,
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

    data class Success(
        private val id: String,
        private val current: Current,
        private val warnings: List<Warning>,
        private val indicator: Indicator,
        private val horizon: Horizon,
    ) : WeatherUi() {
        override fun show(
            locationView: LocationView,
            indicatorsView: IndicatorsView,
            sunriseSunsetView: SunriseSunsetView,
            warningAdapter: WarningAdapter,
        ) {
            locationView.show(current)
            indicatorsView.show(indicator)
            sunriseSunsetView.show(horizon)
            warningAdapter.submitList(warnings)
        }

        override fun show(messageView: TextView) = Visibility.Gone().apply(messageView)

    }
}