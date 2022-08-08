package com.github.skytoph.simpleweather.core.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.github.skytoph.simpleweather.core.Matcher

abstract class BaseDiffUtil<T : Matcher<T>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.matches(newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.contentMatches(newItem)
}