package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.location.cloud.LocationCloud
import com.github.skytoph.simpleweather.data.location.cloud.PlaceData
import com.github.skytoph.simpleweather.data.weather.mapper.LocationCloudToDataMapper
import javax.inject.Inject

interface PlaceCloudToDataMapper : Mapper<PlaceData> {
    fun map(data: LocationCloud, placeId: String): PlaceData

    class Base @Inject constructor(private val mapper: LocationCloudToDataMapper) :
        PlaceCloudToDataMapper {

        override fun map(data: LocationCloud, placeId: String): PlaceData =
            data.map(object : PlaceDataMapper {
                override fun map(
                    name: String,
                    localNames: Map<String, String?>,
                    lat: Double,
                    lng: Double
                ) = PlaceData(placeId, mapper.map(localNames, name), lat, lng)
            })
    }
}

interface PlaceDataMapper : Mapper<PlaceData> {
    fun map(name: String, localNames: Map<String, String?>, lat: Double, lng: Double): PlaceData
}