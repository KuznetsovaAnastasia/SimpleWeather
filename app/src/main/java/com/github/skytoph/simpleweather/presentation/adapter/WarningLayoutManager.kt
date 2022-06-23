package com.github.skytoph.simpleweather.presentation.adapter

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

class WarningLayoutManager(context: Context?) : LinearLayoutManager(context) {
    private val isScrollEnabled = false

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }
}