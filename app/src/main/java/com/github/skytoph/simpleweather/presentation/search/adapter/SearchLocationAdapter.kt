package com.github.skytoph.simpleweather.presentation.search.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseAdapter
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseDiffUtil
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseViewHolder
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi

class SearchLocationAdapter :
    BaseAdapter<SearchItemUi, BaseViewHolder<SearchItemUi>>(
        LocationDiffCallback()) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SearchItemUi.Location -> ViewType.LOCATION.ordinal
        is SearchItemUi.Fail -> ViewType.ERROR.ordinal
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<SearchItemUi> = when (viewType) {
        ViewType.LOCATION.ordinal -> LocationViewHolder.Base(R.layout.search_item.inflateView(parent))
        else -> LocationViewHolder.Error(R.layout.error_fullscreen.inflateView(parent))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<SearchItemUi>, position: Int) =
        holder.bind(getItem(position))

    abstract class LocationViewHolder(itemView: View) : BaseViewHolder<SearchItemUi>(itemView) {

        class Base(itemView: View) : LocationViewHolder(itemView) {

            override fun bind(item: SearchItemUi) {
                val titleTextView = itemView.findViewById<TextView>(R.id.search_item_title)
                val subtitleTextView = itemView.findViewById<TextView>(R.id.search_item_subtitle)
                item.show(titleTextView, subtitleTextView)
            }
        }

        class Error(itemView: View) : LocationViewHolder(itemView) {

            override fun bind(item: SearchItemUi) {
                val messageTextView = itemView.findViewById<TextView>(R.id.error_message_text_view)
                item.show(messageTextView, messageTextView)
            }
        }
    }

    class LocationDiffCallback : BaseDiffUtil<SearchItemUi>()
}

enum class ViewType {
    LOCATION,
    ERROR
}
