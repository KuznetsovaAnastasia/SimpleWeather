package com.github.skytoph.simpleweather.core

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics

interface Logger {
    fun log(tag: String, exception: Exception)

    class Debug : Logger {

        override fun log(tag: String, exception: Exception) {
            Log.e(tag, exception.toString() + "\n" + exception.stackTraceToString())
        }
    }

    class Remote : Logger {

        override fun log(tag: String, exception: Exception) {
            FirebaseCrashlytics.getInstance().recordException(exception)
        }
    }
}