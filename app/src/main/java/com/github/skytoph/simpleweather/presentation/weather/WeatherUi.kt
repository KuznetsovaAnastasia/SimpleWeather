package com.github.skytoph.simpleweather.presentation.weather

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.skytoph.simpleweather.core.presentation.view.IndicatorsView
import com.github.skytoph.simpleweather.core.presentation.view.LocationView
import com.github.skytoph.simpleweather.core.presentation.view.SunriseSunsetView
import com.github.skytoph.simpleweather.domain.weather.RefreshLocation
import com.github.skytoph.simpleweather.presentation.addlocation.Loading
import com.github.skytoph.simpleweather.presentation.addlocation.LoadingCommunication
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent.*

sealed class WeatherUi : ShowWeatherUi() {
    abstract fun show(communication: LoadingCommunication.Update)
    abstract fun show(communication: WeatherCommunication)
    abstract fun saveState(refreshLocation: RefreshLocation.SaveRefreshed)

    data class Base(
        private val id: String,
        private val current: Current,
        private val warnings: List<Warning>,
        private val indicator: Indicator,
        private val hourly: List<HourlyForecast>,
        private val daily: List<DailyForecast>,
        private val horizon: Horizon,
    ) : WeatherUi() {

        override fun show(
            locationView: LocationView,
            indicatorsView: IndicatorsView,
            sunriseSunsetView: SunriseSunsetView,
            recyclerView: RecyclerView,
            submitLists: (List<Warning>, List<HourlyForecast>, List<DailyForecast>) -> Unit,
        ) {
            locationView.show(current)
            indicatorsView.show(indicator)
            sunriseSunsetView.show(horizon)
            submitLists.invoke(warnings, hourly, daily)
            recyclerView.visibility = View.VISIBLE
        }

        override fun show(communication: LoadingCommunication.Update) =
            communication.show(Loading.SUCCESS)

        override fun show(communication: WeatherCommunication) = communication.show(this)

        override fun saveState(refreshLocation: RefreshLocation.SaveRefreshed) =
            refreshLocation.locationRefreshed(id)
    }

    object Fail : WeatherUi() {
        override fun show(communication: LoadingCommunication.Update) =
            communication.show(Loading.FAIL)

        override fun show(communication: WeatherCommunication) = Unit

        override fun saveState(refreshLocation: RefreshLocation.SaveRefreshed) = Unit
    }
}