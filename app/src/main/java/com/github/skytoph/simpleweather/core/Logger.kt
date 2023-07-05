package com.github.skytoph.simpleweather.core

import android.util.Log
import com.github.skytoph.simpleweather.core.exception.EmptyRequestException
import com.github.skytoph.simpleweather.core.exception.NoResultsException
import com.google.firebase.crashlytics.FirebaseCrashlytics

interface Logger {
    fun log(tag: String, exception: Exception)

    abstract class Abstract : Logger {

        override fun log(tag: String, exception: Exception) {
            Log.e(tag, exception.toString() + "\n" + exception.stackTraceToString())
        }

        protected fun isIgnored(exception: Exception) = ignoreList.contains(exception::class.java)
    }

    class Debug : Abstract() {

        override fun log(tag: String, exception: Exception) {
            if (!isIgnored(exception))
                super.log(tag, exception)
        }
    }

    class Remote : Abstract() {

        override fun log(tag: String, exception: Exception) {
            if (!isIgnored(exception)) {
                super.log(tag, exception)
                FirebaseCrashlytics.getInstance().recordException(exception)
            }
        }
    }

    private companion object {
        val ignoreList: List<Class<out Exception>> =
            listOf(
                EmptyRequestException::class.java,
                NoResultsException::class.java
            )
    }
}