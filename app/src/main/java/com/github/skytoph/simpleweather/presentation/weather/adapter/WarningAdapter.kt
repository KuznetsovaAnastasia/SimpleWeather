package com.github.skytoph.simpleweather.presentation.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseDiffUtil
import com.github.skytoph.simpleweather.core.presentation.view.WarningInfoView
import com.github.skytoph.simpleweather.core.presentation.view.WarningRainView
import com.github.skytoph.simpleweather.core.presentation.view.WarningView
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent.Warning
import javax.inject.Inject

class WarningAdapter @Inject constructor() :
    ListAdapter<Warning, WarningAdapter.WarningViewHolder<out WarningView>>(WarningItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarningViewHolder<out WarningView> =
        when (viewType) {
            WarningType.RAINY.ordinal ->
                WarningViewHolder<WarningRainView>(LayoutInflater.from(parent.context)
                    .inflate(R.layout.warning_rain_item, parent, false))
            else ->
                WarningViewHolder<WarningInfoView>(LayoutInflater.from(parent.context)
                    .inflate(R.layout.warning_item, parent, false))
        }

    override fun onBindViewHolder(holder: WarningViewHolder<out WarningView>, position: Int) =
        holder.bind(getItem(position))

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is WeatherUiComponent.WarningBasic -> WarningType.BASIC.ordinal
        is WeatherUiComponent.WarningRain -> WarningType.RAINY.ordinal
    }

    class WarningViewHolder<V : WarningView>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(warning: Warning) {
            itemView.findViewById<V>(R.id.warning_view_item).show(warning)
        }
    }

    class WarningItemDiffCallback : BaseDiffUtil<Warning>()

    enum class WarningType {
        BASIC,
        RAINY
    }
}
