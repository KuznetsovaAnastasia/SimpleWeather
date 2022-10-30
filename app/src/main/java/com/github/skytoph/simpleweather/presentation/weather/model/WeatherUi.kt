package com.github.skytoph.simpleweather.presentation.weather.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.skytoph.simpleweather.core.presentation.view.IndicatorsView
import com.github.skytoph.simpleweather.core.presentation.view.LocationView
import com.github.skytoph.simpleweather.core.presentation.view.SunriseSunsetView
import com.github.skytoph.simpleweather.presentation.addlocation.Loading
import com.github.skytoph.simpleweather.presentation.addlocation.LoadingCommunication
import com.github.skytoph.simpleweather.presentation.weather.WeatherCommunication

sealed class WeatherUi : ShowWeatherUi() {
    abstract fun show(communication: LoadingCommunication.Update)
    abstract fun show(communication: WeatherCommunication)

    data class Base(
        private val id: String,
        private val current: CurrentWeatherUi,
        private val indicator: IndicatorsUi,
        private val horizon: HorizonUi,
        private val listUi: ListUi,
    ) : WeatherUi() {

        override fun show(
            locationView: LocationView,
            indicatorsView: IndicatorsView,
            sunriseSunsetView: SunriseSunsetView,
            recyclerView: RecyclerView,
            submitLists: (List<WarningUi>, List<ForecastUi.Hourly>, List<ForecastUi.Daily>) -> Unit,
        ) {
            locationView.show(current)
            indicatorsView.show(indicator)
            sunriseSunsetView.show(horizon)
            listUi.show(submitLists)
            recyclerView.visibility = View.VISIBLE
        }

        override fun show(communication: LoadingCommunication.Update) =
            communication.show(Loading.SUCCESS)

        override fun show(communication: WeatherCommunication) = communication.show(this)
    }

    object Fail : WeatherUi() {
        override fun show(communication: LoadingCommunication.Update) =
            communication.show(Loading.FAIL)

        override fun show(communication: WeatherCommunication) = Unit
    }
}