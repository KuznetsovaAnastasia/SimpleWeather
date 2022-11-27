package com.github.skytoph.simpleweather.data.search.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.time.CurrentTime
import com.github.skytoph.simpleweather.data.search.cache.SearchHistoryData
import javax.inject.Inject

interface SearchHistoryDataMapper : Mapper<SearchHistoryData> {
    fun map(id: String, location: String, time: Long = -1): SearchHistoryData

    class Base @Inject constructor(private val currentTime: CurrentTime) :
        SearchHistoryDataMapper {

        override fun map(id: String, location: String, time: Long): SearchHistoryData {
            val itemTime = if (time > 0) time else currentTime.inSeconds()
            return SearchHistoryData(id, location, itemTime)
        }
    }
}