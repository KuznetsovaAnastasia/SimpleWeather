package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.data.location.mapper.ToLocationDataMapper
import com.github.skytoph.simpleweather.data.location.model.LocationData
import com.squareup.moshi.Json

data class LocationCloud(
    @field:Json(name="results")
    private val results: List<ResultsCloud>,
): Mappable<LocationData, ToLocationDataMapper>{

    override fun map(mapper: ToLocationDataMapper) = mapper.map(results[0].map())
}

data class ResultsCloud(
    @field:Json(name="address_components")
    private val components: List<ComponentCloud>
): MappableTo<String>{
    override fun map(): String = components[0].map()
}

data class ComponentCloud(
    @field:Json(name="long_name")
    private val name: String
): MappableTo<String>{
    override fun map(): String = name
}
