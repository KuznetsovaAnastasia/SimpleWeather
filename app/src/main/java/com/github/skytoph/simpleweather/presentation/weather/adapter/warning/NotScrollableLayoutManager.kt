package com.github.skytoph.simpleweather.presentation.weather.adapter.warning

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class NotScrollableLayoutManager(context: Context?) : LinearLayoutManager(context) {
    private val isScrollEnabled = false

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }
}