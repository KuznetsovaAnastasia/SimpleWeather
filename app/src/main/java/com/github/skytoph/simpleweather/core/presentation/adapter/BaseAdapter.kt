package com.github.skytoph.simpleweather.core.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.skytoph.simpleweather.core.Matcher

abstract class BaseAdapter<T : Matcher<T>, V : BaseViewHolder<T>>(diffCallback: BaseDiffUtil<T>) :
    ListAdapter<T, V>(diffCallback) {

    override fun onBindViewHolder(holder: V, position: Int) = holder.bind(getItem(position))

    override fun submitList(list: List<T>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    protected fun Int.inflateView(parent: ViewGroup): View = LayoutInflater.from(parent.context)
        .inflate(this, parent, false)
}

