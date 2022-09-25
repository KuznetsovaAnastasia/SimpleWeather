package com.github.skytoph.simpleweather.presentation.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseDiffUtil
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent

class HourlyForecastAdapter :
    ListAdapter<WeatherUiComponent.HourlyForecast, HourlyForecastAdapter.ForecastViewHolder>(
        ForecastItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_hourly_item, parent, false))

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tempTextView = itemView.findViewById<TextView>(R.id.forecast_temp)
        private val timeTextView = itemView.findViewById<TextView>(R.id.forecast_time)
        private val popTextView = itemView.findViewById<TextView>(R.id.forecast_pop)
        private val weatherImageView = itemView.findViewById<ImageView>(R.id.forecast_image)

        fun bind(forecast: WeatherUiComponent.HourlyForecast) =
            forecast.show(weatherImageView, timeTextView, tempTextView, popTextView)
    }

    class ForecastItemDiffCallback : BaseDiffUtil<WeatherUiComponent.HourlyForecast>()
}