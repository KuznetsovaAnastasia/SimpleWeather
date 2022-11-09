package com.github.skytoph.simpleweather.presentation.weather.model

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.skytoph.simpleweather.core.presentation.view.IndicatorsView
import com.github.skytoph.simpleweather.core.presentation.view.LocationView
import com.github.skytoph.simpleweather.core.presentation.view.SunriseSunsetView

abstract class ShowWeatherUi {

    open fun show(
        errorView: TextView,
        locationView: LocationView,
        indicatorsView: IndicatorsView,
        sunriseSunsetView: SunriseSunsetView,
        recyclers: List<RecyclerView>,
        submitLists: (List<WarningUi>, List<ForecastUi.Hourly>, List<ForecastUi.Daily>) -> Unit,
    ) = Unit
}