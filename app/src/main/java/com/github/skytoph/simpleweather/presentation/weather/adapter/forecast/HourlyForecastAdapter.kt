package com.github.skytoph.simpleweather.presentation.weather.adapter.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseDiffUtil
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent

class HourlyForecastAdapter :
    ForecastAdapter<WeatherUiComponent.HourlyForecast>(ForecastItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ForecastViewHolder<WeatherUiComponent.HourlyForecast> =
        WeeklyForecastViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_hourly_item, parent, false))

    class ForecastItemDiffCallback : BaseDiffUtil<WeatherUiComponent.HourlyForecast>()

    class WeeklyForecastViewHolder(itemView: View) :
        ForecastViewHolder<WeatherUiComponent.HourlyForecast>(itemView)
}