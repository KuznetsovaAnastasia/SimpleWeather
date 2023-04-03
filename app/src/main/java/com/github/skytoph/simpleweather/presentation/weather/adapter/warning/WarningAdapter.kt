package com.github.skytoph.simpleweather.presentation.weather.adapter.warning

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
import com.github.skytoph.simpleweather.presentation.weather.model.WarningUi
import javax.inject.Inject

class WarningAdapter @Inject constructor() :
    ListAdapter<WarningUi, WarningAdapter.WarningViewHolder<out WarningView>>(
        WarningItemDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): WarningViewHolder<out WarningView> =
        when (viewType) {
            WarningType.RAINY.ordinal ->
                WarningViewHolder<WarningRainView>(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.warning_rain_item, parent, false)
                )
            else ->
                WarningViewHolder<WarningInfoView>(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.warning_item, parent, false)
                )
        }

    override fun onBindViewHolder(holder: WarningViewHolder<out WarningView>, position: Int) =
        holder.bind(getItem(position))

    override fun getItemViewType(position: Int): Int =
        if (getItem(position) is WarningUi.Rain) WarningType.RAINY.ordinal
        else WarningType.BASIC.ordinal

    class WarningViewHolder<V : WarningView>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(warning: WarningUi) {
            itemView.findViewById<V>(R.id.warning_view_item).show(warning)
        }
    }

    class WarningItemDiffCallback : BaseDiffUtil<WarningUi>()

    enum class WarningType {
        BASIC,
        RAINY
    }
}
