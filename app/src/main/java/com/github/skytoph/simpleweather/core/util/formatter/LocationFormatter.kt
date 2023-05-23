package com.github.skytoph.simpleweather.core.util.formatter

import com.github.skytoph.simpleweather.core.provider.LocaleProvider
import java.util.*
import javax.inject.Inject

interface LocationFormatter {
    fun map(location: Map<String, String>): String

    class Base @Inject constructor(private val resources: LocaleProvider) : LocationFormatter {

        override fun map(location: Map<String, String>): String =
            location.get(resources.locale().language) ?: location.getValue(Locale.ENGLISH.language)
    }
}