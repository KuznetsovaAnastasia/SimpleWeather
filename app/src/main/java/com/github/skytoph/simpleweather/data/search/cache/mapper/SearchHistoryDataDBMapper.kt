package com.github.skytoph.simpleweather.data.search.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.search.cache.model.SearchHistoryDB
import javax.inject.Inject

interface SearchHistoryDataDBMapper : Mapper<SearchHistoryDB> {
    fun map(id: String, location: String, time: Long, dataBase: DataBase): SearchHistoryDB

    class Base @Inject constructor() : SearchHistoryDataDBMapper {

        override fun map(id: String, location: String, time: Long, dataBase: DataBase): SearchHistoryDB =
            dataBase.createObject<SearchHistoryDB>(id).apply {
                this.location = location
            }
    }
}
