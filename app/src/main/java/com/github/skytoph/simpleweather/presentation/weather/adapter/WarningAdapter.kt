package com.github.skytoph.simpleweather.presentation.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseDiffUtil
import com.github.skytoph.simpleweather.core.presentation.view.WarningView
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent.Warning

class WarningAdapter :
    ListAdapter<Warning, WarningAdapter.WarningViewHolder>(WarningItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarningViewHolder =
        WarningViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.warning_item, parent, false))

    override fun onBindViewHolder(holder: WarningViewHolder, position: Int) =
        holder.bind(getItem(position))

    class WarningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(warning: Warning) {
            itemView.findViewById<WarningView>(R.id.warning_view_item).show(warning)
        }
    }

    class WarningItemDiffCallback : BaseDiffUtil<Warning>()

}
