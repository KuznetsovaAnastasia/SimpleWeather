package com.github.skytoph.simpleweather.core.provider

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {
    fun color(@ColorRes id: Int): Int
    fun dimensionPixel(@DimenRes id: Int): Int
    fun string(@StringRes id: Int): String
    fun string(@StringRes id: Int, vararg args: String): String
    fun drawable(@DrawableRes id: Int): Drawable?
}