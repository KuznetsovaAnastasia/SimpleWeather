package com.github.skytoph.simpleweather.data.search.cache

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.search.cache.mapper.SearchHistoryDataDBMapper
import com.github.skytoph.simpleweather.data.search.cache.model.SearchHistoryDB
import com.github.skytoph.simpleweather.domain.search.mapper.SearchHistoryDomainMapper
import com.github.skytoph.simpleweather.domain.search.model.SearchHistoryDomain

data class SearchHistoryData(
    private val id: String,
    private val location: String,
    private val time: Long,
) : Mappable<SearchHistoryDomain, SearchHistoryDomainMapper>,
    MappableToDB.Base<SearchHistoryDB, SearchHistoryDataDBMapper> {

    override fun map(mapper: SearchHistoryDomainMapper): SearchHistoryDomain =
        mapper.map(id, location)

    override fun map(mapper: SearchHistoryDataDBMapper, dataBase: DataBase): SearchHistoryDB =
        mapper.map(id, location, time, dataBase)
}
