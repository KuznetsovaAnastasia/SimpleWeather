package com.github.skytoph.simpleweather.core.data

import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface TimeProvider {
    fun currentTimeSeconds(): Long

    class Base @Inject constructor() : TimeProvider {

        override fun currentTimeSeconds(): Long =
            TimeUnit.MILLISECONDS.toSeconds(Calendar.getInstance().timeInMillis)
    }
}