package com.github.skytoph.simpleweather.presentation.weather.adapter.forecast

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.Matcher
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseDiffUtil
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent

abstract class ForecastAdapter<T : WeatherUiComponent.Forecast>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, ForecastAdapter.ForecastViewHolder<T>>(diffCallback) {

    override fun onBindViewHolder(holder: ForecastViewHolder<T>, position: Int) =
        holder.bind(getItem(position))

    abstract class ForecastViewHolder<T : WeatherUiComponent.Forecast>(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val tempTextView = itemView.findViewById<TextView>(R.id.forecast_temp)
        private val timeTextView = itemView.findViewById<TextView>(R.id.forecast_time)
        private val popTextView = itemView.findViewById<TextView>(R.id.forecast_pop)
        private val weatherImageView = itemView.findViewById<ImageView>(R.id.forecast_image)

        open fun bind(forecast: T) =
            forecast.show(weatherImageView, timeTextView, tempTextView, popTextView)
    }

    abstract class ForecastItemDiffCallback<T : Matcher<T>> : BaseDiffUtil<T>()
}