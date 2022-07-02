package com.github.skytoph.simpleweather.core.presentation.view.horizon

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat

interface ResourceProvider {
    fun getColor(@ColorRes id: Int): Int
    fun getDimensionPixel(@DimenRes id: Int): Int
    fun getString(@StringRes id: Int): String
    fun getDrawable(@DrawableRes id: Int): Drawable?

    class Base(private val resources: Resources) : ResourceProvider {
        override fun getColor(id: Int) = ResourcesCompat.getColor(resources, id, null)
        override fun getDimensionPixel(id: Int): Int = resources.getDimensionPixelSize(id)
        override fun getString(id: Int): String = resources.getString(id)
        override fun getDrawable(id: Int): Drawable? =
            ResourcesCompat.getDrawable(resources, id, null)
    }
}