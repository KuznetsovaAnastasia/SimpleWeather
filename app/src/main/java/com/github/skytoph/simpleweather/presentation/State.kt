package com.github.skytoph.simpleweather.presentation

import android.view.View
import android.widget.TextView
import com.github.skytoph.simpleweather.presentation.WeatherUiComponent.*
import com.github.skytoph.simpleweather.presentation.adapter.WarningAdapter
import com.github.skytoph.simpleweather.presentation.view.IndicatorsView
import com.github.skytoph.simpleweather.presentation.view.LocationView
import com.github.skytoph.simpleweather.presentation.view.SunriseSunsetView
import com.github.skytoph.simpleweather.presentation.view.WarningView

sealed class State {
    fun show(
        locationView: LocationView,
        indicatorsView: IndicatorsView,
        sunriseSunsetView: SunriseSunsetView,
        warningAdapter: WarningAdapter,
        messageTextView: TextView,
    ) {
        show(locationView, indicatorsView, sunriseSunsetView, warningAdapter)
        show(messageTextView)
    }

    open fun show(
        locationView: LocationView,
        indicatorsView: IndicatorsView,
        sunriseSunsetView: SunriseSunsetView,
        warningAdapter: WarningAdapter,
    ) = Unit

    open fun show(textView: TextView) = Unit

    class Base(
        private val current: Current,
        private val warnings: List<Warning>,
        private val indicator: Indicator,
        private val horizon: Horizon,
    ) : State() {
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

        override fun show(textView: TextView) {
            textView.visibility = View.GONE
        }
    }

    object Progress : State() {

//        override fun show(
//            locationView: LocationView,
//            indicatorsView: IndicatorsView,
//            sunriseSunsetView: SunriseSunsetView,
//            warningAdapter: WarningAdapter,
//        ) {
//            locationView.visibility = View.GONE
//            indicatorsView.visibility = View.GONE
//            sunriseSunsetView.visibility = View.GONE
//            warningAdapter.submitList(emptyList())
//        }
    }

    class Fail(private val message: String) : State() {
        override fun show(textView: TextView) {
            textView.visibility = View.VISIBLE
            textView.text = message
        }

        override fun show(
            locationView: LocationView,
            indicatorsView: IndicatorsView,
            sunriseSunsetView: SunriseSunsetView,
            warningAdapter: WarningAdapter,
        ) {
            locationView.visibility = View.GONE
            indicatorsView.visibility = View.GONE
            sunriseSunsetView.visibility = View.GONE
            warningAdapter.submitList(emptyList())
        }
    }
}