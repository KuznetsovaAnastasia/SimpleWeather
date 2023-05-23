package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import java.util.*
import javax.inject.Inject

interface LocalNameDataMapper : Mapper<Pair<String, String>> {

    fun map(language: String = "en", name: String): Pair<String, String>
    fun map(language: Locale, name: String): Pair<String, String>

    class Base @Inject constructor() : LocalNameDataMapper {
        override fun map(language: String, name: String) = language to name
        override fun map(language: Locale, name: String): Pair<String, String> =
            map(language.language, name)
    }
}