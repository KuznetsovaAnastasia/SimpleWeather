package com.github.skytoph.simpleweather.presentation.weather.model

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import com.github.skytoph.simpleweather.core.presentation.view.IndicatorsView
import com.github.skytoph.simpleweather.core.presentation.view.LocationView
import com.github.skytoph.simpleweather.core.presentation.view.SunriseSunsetView
import com.github.skytoph.simpleweather.core.presentation.view.visibility.Visibility
import com.github.skytoph.simpleweather.presentation.addlocation.LoadingState
import com.github.skytoph.simpleweather.presentation.addlocation.communication.WeatherLoadingCommunication

sealed class WeatherUi : ShowWeatherUi() {
    abstract fun show(communication: WeatherLoadingCommunication.Update)
    abstract fun show(communication: Communication.Update<WeatherUi>)

    data class Base(
        private val id: String,
        private val current: CurrentWeatherUi,
        private val indicator: IndicatorsUi,
        private val horizon: HorizonUi,
        private val listUi: ListUi,
    ) : WeatherUi() {

        override fun show(
            errorView: TextView,
            locationView: LocationView,
            indicatorsView: IndicatorsView,
            sunriseSunsetView: SunriseSunsetView,
            recyclers: List<RecyclerView>,
            submitLists: (List<WarningUi>, List<ForecastUi.Hourly>, List<ForecastUi.Daily>) -> Unit,
        ) {
            locationView.show(current)
            indicatorsView.show(indicator)
            sunriseSunsetView.show(horizon)
            Visibility.Visible()
                .apply(locationView, indicatorsView, sunriseSunsetView, *recyclers.toTypedArray())
            Visibility.Gone().apply(errorView)
            listUi.show(submitLists)
        }

        override fun show(communication: WeatherLoadingCommunication.Update) =
            communication.show(LoadingState.Success)

        override fun show(communication: Communication.Update<WeatherUi>) = communication.show(this)
    }

    data class Outdated(private val id: String, private val location: String) : WeatherUi() {

        override fun show(
            errorView: TextView,
            locationView: LocationView,
            indicatorsView: IndicatorsView,
            sunriseSunsetView: SunriseSunsetView,
            recyclers: List<RecyclerView>,
            submitLists: (List<WarningUi>, List<ForecastUi.Hourly>, List<ForecastUi.Daily>) -> Unit,
        ) {
            Visibility.Visible().apply(errorView)
            Visibility.Gone().apply(indicatorsView, sunriseSunsetView, *recyclers.toTypedArray())
            locationView.errorState()
        }

        override fun show(communication: WeatherLoadingCommunication.Update) =
            communication.show(LoadingState.Fail)

        override fun show(communication: Communication.Update<WeatherUi>) = Unit
    }

    object Fail : WeatherUi() {
        override fun show(communication: WeatherLoadingCommunication.Update) =
            communication.show(LoadingState.Fail)

        override fun show(communication: Communication.Update<WeatherUi>) = Unit
    }
}