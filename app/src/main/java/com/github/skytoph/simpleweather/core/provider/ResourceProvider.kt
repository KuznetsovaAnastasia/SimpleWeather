package com.github.skytoph.simpleweather.core.provider

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ResourceProvider {

    fun string(@StringRes id: Int): String

    class Base @Inject constructor(@ApplicationContext private val context: Context) :
        ResourceProvider {

        override fun string(id: Int): String = context.getString(id)
    }
}