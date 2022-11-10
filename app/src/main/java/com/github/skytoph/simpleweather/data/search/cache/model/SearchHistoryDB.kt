package com.github.skytoph.simpleweather.data.search.cache.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.search.cache.SearchHistoryData
import com.github.skytoph.simpleweather.data.search.cache.mapper.SearchHistoryDataMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmField

open class SearchHistoryDB : RealmObject(), Mappable<SearchHistoryData, SearchHistoryDataMapper> {
    @PrimaryKey
    @RealmField(name = FIELD_PLACE_ID)
    var id: String = ""

    @RealmField(name = FIELD_LOCATION)
    var location: String = ""

    @RealmField(name = FIELD_TIME)
    var time: Long = 0L

    companion object {
        const val FIELD_PLACE_ID = "place_id"
        const val FIELD_LOCATION = "location"
        const val FIELD_TIME = "time"
    }

    override fun map(mapper: SearchHistoryDataMapper): SearchHistoryData = mapper.map(id, location, time)
}