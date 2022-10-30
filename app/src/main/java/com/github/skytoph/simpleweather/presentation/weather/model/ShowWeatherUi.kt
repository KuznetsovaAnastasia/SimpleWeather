package com.github.skytoph.simpleweather.presentation.weather.model

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
        submitLists: (List<WarningUi>, List<ForecastUi.Hourly>, List<ForecastUi.Daily>) -> Unit,
    ) = Unit
}
