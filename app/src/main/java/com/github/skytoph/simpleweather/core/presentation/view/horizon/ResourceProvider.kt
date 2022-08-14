package com.github.skytoph.simpleweather.core.presentation.view.horizon

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import javax.inject.Inject

interface ResourceProvider {
    fun color(@ColorRes id: Int): Int
    fun dimensionPixel(@DimenRes id: Int): Int
    fun string(@StringRes id: Int): String
    fun string(@StringRes id: Int, vararg args: String): String
    fun drawable(@DrawableRes id: Int): Drawable?

    class Base @Inject constructor(private val resources: Resources) : ResourceProvider {
        override fun color(id: Int) = ResourcesCompat.getColor(resources, id, null)
        override fun dimensionPixel(id: Int): Int = resources.getDimensionPixelSize(id)
        override fun string(id: Int): String = resources.getString(id)
        override fun string(id: Int, vararg args: String): String = resources.getString(id, *args)
        override fun drawable(id: Int): Drawable? =
            ResourcesCompat.getDrawable(resources, id, null)
    }
}