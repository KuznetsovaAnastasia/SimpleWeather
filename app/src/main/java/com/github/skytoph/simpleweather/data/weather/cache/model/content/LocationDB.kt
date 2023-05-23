package com.github.skytoph.simpleweather.data.weather.cache.model.content

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.location.mapper.LocalNameDataMapper
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField

@RealmClass(embedded = true)
open class LocationDB : RealmObject(), Mappable<Pair<String, String>, LocalNameDataMapper> {

    @RealmField(name = FIELD_NAME)
    var name: String = ""

    @RealmField(name = FIELD_LANGUAGE)
    var language: String = ""

    companion object {
        const val FIELD_LANGUAGE = "language"
        const val FIELD_NAME = "name"
    }

    override fun map(mapper: LocalNameDataMapper) = mapper.map(language, name)
}