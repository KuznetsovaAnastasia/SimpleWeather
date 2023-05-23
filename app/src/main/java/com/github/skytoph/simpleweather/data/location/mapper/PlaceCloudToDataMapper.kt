package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.location.cloud.PlaceData
import com.github.skytoph.simpleweather.data.weather.mapper.LocationCloudToDataMapper
import javax.inject.Inject

interface PlaceCloudToDataMapper : Mapper<PlaceData> {
    fun map(name: String, localNames: Map<String, String?>, lat: Double, lng: Double): PlaceData

    class Base @Inject constructor(
        private val idMapper: IdMapper,
        private val mapper: LocationCloudToDataMapper
    ) : PlaceCloudToDataMapper {

        override fun map(
            name: String, localNames: Map<String, String?>, lat: Double, lng: Double
        ): PlaceData = PlaceData(idMapper.map(lat, lng), mapper.map(localNames, name), lat, lng)
    }
}