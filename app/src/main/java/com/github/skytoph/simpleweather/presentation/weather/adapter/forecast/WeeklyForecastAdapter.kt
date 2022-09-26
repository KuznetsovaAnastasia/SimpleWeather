package com.github.skytoph.simpleweather.presentation.weather.adapter.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseDiffUtil
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent

class WeeklyForecastAdapter :
    ForecastAdapter<WeatherUiComponent.WeeklyForecast>(ForecastItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ForecastViewHolder<WeatherUiComponent.WeeklyForecast> =
        WeeklyForecastViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_weekly_item, parent, false))

    class ForecastItemDiffCallback : BaseDiffUtil<WeatherUiComponent.WeeklyForecast>()

    class WeeklyForecastViewHolder(itemView: View) :
        ForecastViewHolder<WeatherUiComponent.WeeklyForecast>(itemView) {

        private val minTempTextView = itemView.findViewById<TextView>(R.id.forecast_temp_min)

        override fun bind(forecast: WeatherUiComponent.WeeklyForecast) {
            forecast.show(minTempTextView)
            super.bind(forecast)
        }
    }
}