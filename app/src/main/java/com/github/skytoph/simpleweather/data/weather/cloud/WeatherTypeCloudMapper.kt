package com.github.skytoph.simpleweather.data.weather.cloud

import com.github.skytoph.simpleweather.core.Mapper

interface WeatherTypeCloudMapper: Mapper<Pair<Int, String>> {

    fun map(id: Int, name: String):Pair<Int, String>

    class Base: WeatherTypeCloudMapper{
        override fun map(id: Int, name: String): Pair<Int, String> = Pair(id, name)
    }
}
