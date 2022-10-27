package com.github.skytoph.simpleweather.presentation.weather.adapter.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseDiffUtil
import com.github.skytoph.simpleweather.presentation.weather.ForecastUi

class DailyForecastAdapter :
    ForecastAdapter<ForecastUi.Daily>(ForecastItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ForecastViewHolder<ForecastUi.Daily> =
        WeeklyForecastViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_daily_item, parent, false))

    class ForecastItemDiffCallback : BaseDiffUtil<ForecastUi.Daily>()

    class WeeklyForecastViewHolder(itemView: View) :
        ForecastViewHolder<ForecastUi.Daily>(itemView) {

        private val minTempTextView = itemView.findViewById<TextView>(R.id.forecast_temp_min)

        override fun bind(forecast: ForecastUi.Daily) {
            forecast.show(minTempTextView)
            super.bind(forecast)
        }
    }
}